package com.example.demo.sample.service;

import com.example.demo.sample.vo.Sample;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SampleService {

	Mono<Sample> findById(Integer id);

	Flux<Sample> findByName(String name);

	Flux<Sample> findAll();

	void create(Sample e);

	Mono<Sample> update(Sample e);

	Mono<Sample> delete(Integer id);

}
