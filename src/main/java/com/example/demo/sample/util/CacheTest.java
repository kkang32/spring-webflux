package com.example.demo.sample.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.example.demo.sample.dao.SampleDAO;
import com.example.demo.sample.vo.UserVO;

@Component
public class CacheTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SampleDAO sampleDao;
	
	@Cacheable(value = "persons" , key = "#id")
	public List<UserVO> test(String id) {
		
		logger.info("in method");
		
		return sampleDao.selectTest();
	}
	
}
