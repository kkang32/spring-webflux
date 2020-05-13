package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.reactive.GenericReactiveTransaction;
import org.springframework.transaction.reactive.TransactionSynchronizationManager;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.example.demo.ReactiveRequestContextHolder;
import com.example.demo.r2dbc.controller.UserController;

import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Mono;

public class CustomR2dbcTransactionManager extends R2dbcTransactionManager{

	private static Logger log = LoggerFactory.getLogger(CustomR2dbcTransactionManager.class);
	private boolean allowCommit = true;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public CustomR2dbcTransactionManager(ConnectionFactory connectionFactory) {
		super(connectionFactory);
	}
	
	

	@Override
	protected Mono<Void> doCommit(TransactionSynchronizationManager transactionSynchronizationManager,
			GenericReactiveTransaction status) throws TransactionException {
		
		ServerHttpRequest request = (ServerHttpRequest) RequestContextHolder.getRequestAttributes().getAttribute("reqest", RequestAttributes.SCOPE_REQUEST);
		log.info("doCommit : " + request.getQueryParams().get("test").get(0));
		
		if (isAllowCommit()) {
			return super.doCommit(transactionSynchronizationManager, status);
		}else {
			return super.doRollback(transactionSynchronizationManager, status);
		}
		
		
		
	}



	public boolean isAllowCommit() {
		return allowCommit;
	}



	public void setAllowCommit(boolean allowCommit) {
		this.allowCommit = allowCommit;
	}

	
	
	
	
}
