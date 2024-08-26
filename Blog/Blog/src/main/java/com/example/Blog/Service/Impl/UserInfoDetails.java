package com.example.Blog.Service.Impl;

import java.util.Arrays;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.Blog.Entity.User;


public class UserInfoDetails implements UserDetails{


	
	 String username;
	
	 String password;
	 
	 List<GrantedAuthority> authorities;
	
	
	
	public UserInfoDetails(User userInfo) {
		 username=userInfo.getUserName();
		 password=userInfo.getPassword();
		authorities=Arrays.stream(userInfo.getRoles()
				.split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

}
