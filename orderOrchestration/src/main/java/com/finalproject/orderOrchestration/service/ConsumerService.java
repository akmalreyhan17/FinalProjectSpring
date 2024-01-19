package com.finalproject.orderOrchestration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.finalproject.orderOrchestration.model.OrderAPI;
import com.finalproject.orderOrchestration.repository.OrchestrationRepository;
import com.finalproject.orderOrchestration.repository.TransRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ConsumerService {
    
    private final OrchestrationRepository orchestrationRepository;
    private final TransRepository transRepository;
    private final PublisherService publisherService;

    @Autowired
    public ConsumerService(OrchestrationRepository orchestrationRepository, TransRepository transRepository, PublisherService publisherService) {
        this.orchestrationRepository = orchestrationRepository;
        this.transRepository = transRepository;
        this.publisherService = publisherService;
    }

    Mono<Integer> priority = Mono.just(1);
    Mono<String> statCheck = Mono.just("SUCCESS");
    Mono<OrderAPI> body = Mono.just(new OrderAPI());
    Mono<Boolean> revert = Mono.just(false);
    Integer sum = 1;

    private Mono<Void> orderProcess() {
        statCheck.filter(s->s.equals("RUNNING")).subscribe(t->{
            revert.subscribe(y->{
                if(y==false){
                    priority.subscribe(u->{
                        body.subscribe(w->{
                            orchestrationRepository.getQueue(w.getOrderType(), u).subscribe(v->{
                                priority = Mono.just(u + 1);
                                body = Mono.just(w);
                                revert = Mono.just(false);
                                publisherService.send(v, w);
                                if(u==sum){statCheck=Mono.just("COMPLETE");} else {statCheck=Mono.just("RUNNING");}
                            });
                        });
                    });
                } else {
                    priority.subscribe(u->{
                        body.subscribe(w->{
                            orchestrationRepository.getRollbackQueue(w.getOrderType(), u-2).subscribe(v->{
                                w.setCrudType("DELETE");
                                publisherService.send(v, w);
                                revert = Mono.just(true);
                                statCheck=Mono.just("COMPLETE");
                            });
                        });
                    });
                }
            });
        });
        
        return Mono.empty();
    }

    @JmsListener(destination = "queue.order.process")
    public void receive(Message<OrderAPI> message) {
        log.info("received message: {}", message);

        OrderAPI data = message.getPayload();
        if(data.getNewOrder()){
            priority = Mono.just(1);
            statCheck = Mono.just("RUNNING");
            body = Mono.just(data);
            revert = Mono.just(false);
            orchestrationRepository.getSumOfQueue(data.getOrderType()).subscribe(x->{sum = x;});
            data.setNewOrder(false);
            orderProcess().subscribe();
        } else {
            transRepository.getStatus(data.getId()).subscribe(x->{
                log.info("status: {}", x);
                if (x.equals("SUCCESS")) {
                    orderProcess().subscribe();
                } else {
                    revert = Mono.just(true);
                    orderProcess().subscribe();
                }
            });
        }   
    }

}
