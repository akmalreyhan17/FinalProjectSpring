package com.finalproject.orderOrchestration.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finalproject.orderOrchestration.model.ActionRegister;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface OrchestrationRepository extends R2dbcRepository<ActionRegister, String> {
        @Query("select queue_name \r\n" + //
                        "from action_register join steps on action_register.id = steps.action_id \r\n" + //
                        "where action_register.action_name = :actionName and steps.priority = :priority")
        Mono<String> getQueue(@Param("actionName") String actionName, @Param("priority") Integer priority);

        @Query("select queue_name\r\n" + //
                        "from action_register join steps on action_register.id = steps.action_id\r\n" + //
                        "where action_register.action_name = :actionName\r\n" + //
                        "order by steps.priority limit :priority")
        Flux<String> getRollbackQueue(@Param("actionName") String actionName, @Param("priority") Integer priority);

        @Query("select count(queue_name)\r\n" + //
                        "from action_register join steps on action_register.id = steps.action_id \r\n" + //
                        "where action_register.action_name = :actionName")
        Mono<Integer> getSumOfQueue(@Param("actionName") String actionName);
}
