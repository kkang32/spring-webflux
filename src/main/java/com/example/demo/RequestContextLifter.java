package com.example.demo;

import java.util.Map;
import java.util.stream.Collectors;

import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.context.request.RequestContextHolder;

import com.example.ChamomileRequestAttributes;

import reactor.core.CoreSubscriber;
import reactor.util.context.Context;

public class RequestContextLifter<T> implements CoreSubscriber<T> {

	private static Logger log = LoggerFactory.getLogger(RequestContextLifter.class);
	
	CoreSubscriber<T> coreSubscriber;

    public RequestContextLifter(CoreSubscriber<T> coreSubscriber) {
        this.coreSubscriber = coreSubscriber;
    }
	
    @Override
    public void onSubscribe(Subscription subscription) {
    	copyToMdc(coreSubscriber.currentContext(), "onSubscribe");
        coreSubscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(T obj) {
        copyToMdc(coreSubscriber.currentContext(), "onNext");
        coreSubscriber.onNext(obj);
    }

    @Override
    public void onError(Throwable t) {
    	copyToMdc(coreSubscriber.currentContext(), "onError");
        coreSubscriber.onError(t);
    }

    @Override
    public void onComplete() {
    	copyToMdc(coreSubscriber.currentContext(), "onError");
        coreSubscriber.onComplete();
    }

    @Override
    public Context currentContext() {
        return coreSubscriber.currentContext();
    }

    /**
     * Extension function for the Reactor [Context]. Copies the current context to the MDC, if context is empty clears the MDC.
     * State of the MDC after calling this method should be same as Reactor [Context] state.
     * One thread-local access only.
     */
    private void copyToMdc(Context context, String method) {

    	log.info("{} 이벤트 복사 . 컨텍스트는 {}", method, context.isEmpty() ? "비었다." : "뭔가 있다.");
        if (!context.isEmpty()) {
            Map<String, Object> map = context.stream()
                    .collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue()));
            if (map.get(ReactiveRequestContextHolder.CONTEXT_KEY) instanceof ServerHttpRequest) {
            	log.info("{} 여기에 serverHttpRequest가 있다.", method);
            	RequestContextHolder.setRequestAttributes(new ChamomileRequestAttributes((ServerHttpRequest)map.get(ReactiveRequestContextHolder.CONTEXT_KEY)), true);
            }
            
        } else {
        	RequestContextHolder.resetRequestAttributes();
        }
    }

}
