package com.example;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.context.request.RequestAttributes;

public class ChamomileRequestAttributes implements RequestAttributes {

	ServerHttpRequest attr;
	
	
	
	public ChamomileRequestAttributes(ServerHttpRequest request) {
		setAttribute("request", request, SCOPE_REQUEST);
	}
	
	
	@Override
	public Object getAttribute(String name, int scope) {
		// TODO Auto-generated method stub
		return attr;
	}

	@Override
	public void setAttribute(String name, Object value, int scope) {
		// TODO Auto-generated method stub
		attr = (ServerHttpRequest) value;
	}

	@Override
	public void removeAttribute(String name, int scope) {
		// TODO Auto-generated method stub
		attr = null;
	}

	@Override
	public String[] getAttributeNames(int scope) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback, int scope) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object resolveReference(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSessionId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getSessionMutex() {
		// TODO Auto-generated method stub
		return null;
	}

}
