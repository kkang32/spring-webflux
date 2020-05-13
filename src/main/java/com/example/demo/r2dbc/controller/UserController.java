package com.example.demo.r2dbc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.example.demo.r2dbc.service.UserService;
import com.example.demo.r2dbc.vo.User;

import reactor.core.publisher.Flux;

@RestController
public class UserController {
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
    private UserService userService;
	
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Flux<User> getAll() {		
		ServerHttpRequest request = (ServerHttpRequest) RequestContextHolder.getRequestAttributes().getAttribute("reqest", RequestAttributes.SCOPE_REQUEST);
		log.info("controller : " + request.getQueryParams().get("test").get(0));
        return userService.test2();
    }
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
    public Flux<User> get() {
		
        return userService.test2();
    }
}
