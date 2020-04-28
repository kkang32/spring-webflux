package com.example.demo;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.reactivestreams.Publisher;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
@Aspect
public class CustomAspect {
	@Around("execution(* com.example.demo..*.*Service*.*(..))")
	public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
		
		//annotation check
		//return 값 체크
		//캐시처리 일때 cacheManager에서 값꺼내오기
			//키값이 annotation에 정의 되어있다면 그걸로.
			//아니면 parameter
		//cachemanager에 없다면proceed
		//가져와서 cacheManager에 값 넣기
			//마찬가지로 키값이 정이되어있다면 그걸로
			//아니면 parameter
		
		MethodSignature signature = (MethodSignature) pjp.getSignature();
	    Method method = signature.getMethod();
	    Cacheable cache = method.getAnnotation(Cacheable.class);
	    if (cache != null) {
	    	if (method.getReturnType().isAssignableFrom(Publisher.class)) {
	    		System.out.println("return type is : " + method.getReturnType().getCanonicalName());
	    	}
	    }
	    
		long begin = System.currentTimeMillis();
		Object retVal = pjp.proceed(); // 메서드 호출 자체를 감쌈
		System.out.println(System.currentTimeMillis() - begin);
		return retVal;
	}
}
