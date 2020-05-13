package com.example.demo.r2dbc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.ReactiveRequestContextHolder;
import com.example.demo.r2dbc.vo.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
	private static Logger log = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private DatabaseClient databaseClient;
	
	
	
	@Transactional
	public Mono<Void> test() {
		Mono<Void> retval = databaseClient.execute("delete from user where user_id=:userId")
		.bind("userId", "admin")
		.then();
		retval.subscribe();
		return retval;
	}
	
	@Transactional
	public Flux<User> test2() {
		
		
		
		return databaseClient
				.execute("delete from user where user_id=:userId")
					.bind("userId", "admin")
				.fetch()
				.rowsUpdated()
					.flatMapMany(m -> 
									databaseClient
										.execute("select * from user where user_id = :userId")
											.bind("userId", "admin")
										.as(User.class)
										.fetch()
										.all()
								);
	}
	
	private void test(ServerHttpRequest r) {
		log.info("::: get : " + r.getQueryParams().get("test"));
	}
	
}
