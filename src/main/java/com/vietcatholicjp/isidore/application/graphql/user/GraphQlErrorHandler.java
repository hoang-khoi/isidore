package com.vietcatholicjp.isidore.application.graphql.user;

import com.vietcatholicjp.isidore.domain.exceptions.ClientException;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GenericGraphQLError;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class GraphQlErrorHandler {
    @ExceptionHandler(ClientException.class)
    public GraphQLError handleClientException(ClientException e) {
        return new GenericGraphQLError(e.getMessage());
    }
}
