package com.example.demo.sample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.sample.dao.SampleDAO;
import com.example.demo.sample.util.CacheTest;
import com.example.demo.sample.vo.Sample;
import com.example.demo.sample.vo.UserVO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SampleServiceImpl implements SampleService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private List<Sample> repository;
	
	@Autowired
	private SampleDAO sampleDao;
	
	@Autowired
	private CacheTest ct;
	
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

	@Async
	@Override
	public CompletableFuture<List<UserVO>> findAll() {
		//Flux<Sample> sampleFlux = Flux.fromIterable(this.repository);
		//return sampleFlux;
		
		List<UserVO> userList = sampleDao.selectTest();
		
		//Flux<UserVO> sampleFlux = Flux.fromIterable(userList);
		
		//return sampleFlux;
		return CompletableFuture.completedFuture(userList);
	}

	
	
	@Override	
	public Flux<UserVO> findAllSync() {
		List<UserVO> userList = ct.test("id");
		
		Flux<UserVO> sampleFlux = Flux.fromIterable(userList);
		
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
