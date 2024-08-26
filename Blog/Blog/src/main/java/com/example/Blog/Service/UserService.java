package com.example.Blog.Service;

import java.util.List;


import com.example.Blog.payload.UserDto;

public interface UserService {

	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,int userId);
	UserDto getUserById(int userId);
	List<UserDto> getAllUser();
	void deleteUser(int userId);
}
