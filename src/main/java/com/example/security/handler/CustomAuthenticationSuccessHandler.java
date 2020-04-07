package com.example.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

public class CustomAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

	@Override
	public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
		
		
		Mono<SecurityContext> context = ReactiveSecurityContextHolder.getContext();
		context
		.map(SecurityContext::getAuthentication)
        .map(Authentication::getName)
        .flatMap(s -> Mono.just("Hi " + s))
        .doOnNext(System.out::println)
        .doOnError(Throwable::printStackTrace)
        .doOnSuccess(s -> System.out.println("completed without value: " + s))
        .flatMap(s -> ServerResponse.ok().build());
        
		return null;
	}

}
