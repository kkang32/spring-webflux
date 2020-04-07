package com.example.demo.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.sample.vo.Sample;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SampleServiceImpl implements SampleService {

	private List<Sample> repository;
	
	
	public SampleServiceImpl() {
		repository = new ArrayList<Sample>();
		
		Sample sample1 = new Sample("admin", "어드민", 15);
		Sample sample2 = new Sample("user", "유저", 18);
		Sample sample3 = new Sample("jack", "잭", 22);
		Sample sample4 = new Sample("cup", "컵", 25);
		Sample sample5 = new Sample("computer", "컴퓨터", 36);
		Sample sample6 = new Sample("monitor", "모니터", 58);
		
		repository.add(sample1);
		repository.add(sample2);
		repository.add(sample3);
		repository.add(sample4);
		repository.add(sample5);
		repository.add(sample6);
		
		
	}
	
	
	@Override
	public Mono<Sample> findById(Integer id) {
		
		return null;
	}

	@Override
	public Flux<Sample> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<Sample> findAll() {
		Flux<Sample> sampleFlux = Flux.fromIterable(this.repository);
		return sampleFlux;
	}

	@Override
	public void create(Sample e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Mono<Sample> update(Sample e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Sample> delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
