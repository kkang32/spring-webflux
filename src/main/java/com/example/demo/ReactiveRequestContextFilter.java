package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.example.ChamomileRequestAttributes;

import reactor.core.publisher.Mono;

@Component
public class ReactiveRequestContextFilter implements WebFilter {
	private static Logger log = LoggerFactory.getLogger(ReactiveRequestContextFilter.class);
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		log.info("::: set : " + request.getQueryParams().get("test"));
		
		
		
		RequestContextHolder.setRequestAttributes(new ChamomileRequestAttributes(request), true);
		
        return chain.filter(exchange)
            .subscriberContext(ctx -> ctx.put(ReactiveRequestContextHolder.CONTEXT_KEY, request));
	}

}
