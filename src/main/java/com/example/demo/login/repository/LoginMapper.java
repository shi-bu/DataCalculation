package com.example.demo.login.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.login.model.User;

@Mapper
public interface LoginMapper {
	
	public User findByUserId(String userId);
}
