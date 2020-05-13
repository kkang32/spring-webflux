package com.example.demo.r2dbc.dao;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.r2dbc.vo.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<User, String> {

	@Query("select * from user where user_id = :userId")
	Flux<User> findByUserId(String userId);
	
	@Query("delete from user where user_id = :userId")
	Mono<Void> deleteById(String userId);
}
