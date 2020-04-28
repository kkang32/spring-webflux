package com.example.demo.sample.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.example.demo.sample.vo.Sample;
import com.example.demo.sample.vo.UserVO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SampleService {

	Mono<Sample> findById(Integer id);

	Flux<Sample> findByName(String name);

	CompletableFuture<List<UserVO>> findAll();

	Flux<UserVO> findAllSync();
	
	void create(Sample e);

	Mono<Sample> update(Sample e);

	Mono<Sample> delete(Integer id);

}
