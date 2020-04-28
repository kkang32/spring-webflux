package com.example.demo.sample.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.sample.service.SampleService;
import com.example.demo.sample.vo.Sample;
import com.example.demo.sample.vo.UserVO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SampleController {
    @Autowired
    private SampleService sampleService;
 
    @RequestMapping(value = { "/create", "/" }, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Sample e) {
    	sampleService.create(e);
    }
 
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Mono<Sample>> findById(@PathVariable("id") Integer id) {
        Mono<Sample> e = sampleService.findById(id);
        HttpStatus status = e != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Mono<Sample>>(e, status);
    }
 
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public Flux<Sample> findByName(@PathVariable("name") String name) {
        return sampleService.findByName(name);
    }
 
    @RequestMapping(value = "/async", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserVO> findAll() {
    	CompletableFuture<List<UserVO>> emps = sampleService.findAll();
    	List<UserVO> userList = new ArrayList<UserVO>();
		try {
			userList = emps.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return Flux.fromIterable(userList);
    }
    
    @RequestMapping(value = "/sync", method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserVO> findAllSync() {
    	Flux<UserVO> emps = sampleService.findAllSync();
    			
    	
        return emps;
    }
 
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Mono<Sample> update(@RequestBody Sample e) {
        return sampleService.update(e);
    }
 
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
    	sampleService.delete(id).subscribe();
    }
 

}
