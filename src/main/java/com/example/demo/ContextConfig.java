package com.example.demo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Hooks;
import reactor.core.publisher.Operators;

@Configuration
public class ContextConfig{
	
	
	private String REQUEST_CONTEXT_REACTOR_KEY = "requestContext";

    @PostConstruct
    private void contextOperatorHook() {
        Hooks.onEachOperator(REQUEST_CONTEXT_REACTOR_KEY,
                Operators.lift((scannable, coreSubscriber) -> new RequestContextLifter<>(coreSubscriber))
        );
    }

    @PreDestroy
    private void cleanupHook() {
        Hooks.resetOnEachOperator(REQUEST_CONTEXT_REACTOR_KEY);
    }
	
	
	


}
