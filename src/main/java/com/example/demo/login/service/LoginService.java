package com.example.demo.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.login.model.User;
import com.example.demo.login.repository.LoginMapper;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private LoginMapper loginMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = loginMapper.findByUserId(username);
		
		if (user == null) {
			throw new UsernameNotFoundException(username + " is not found");
		}
		
		return user;
	}
	
}
