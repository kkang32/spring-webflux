package com.example.demo.sample.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.sample.vo.UserVO;

@Repository
@Mapper
public interface SampleDAO {
	List<UserVO> selectTest();
}
