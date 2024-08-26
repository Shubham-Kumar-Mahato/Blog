package com.example.Blog.controller;

import java.util.List;
 



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Blog.Service.UserService;

import com.example.Blog.payload.ApiResponse;

import com.example.Blog.payload.UserDto;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@PostMapping("/createuser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		
		UserDto createUser=this.userService.createUser(userDto);
		return new ResponseEntity<>(createUser,HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable int userId ){
		
		UserDto updatedUserDto=this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUserDto);
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable int userId){
		UserDto userDto=this.userService.getUserById(userId);
		return ResponseEntity.ok(userDto);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser(){
		
		List<UserDto> list=this.userService.getAllUser();
		
		return ResponseEntity.ok(list);
		
	}
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable int userId){
		this.userService.deleteUser(userId);
		 return new ResponseEntity<>(new ApiResponse("user deleted successfully",true),HttpStatus.OK);
		
	}
	
	

}
