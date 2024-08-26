package com.example.Blog.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Blog.Entity.User;
import com.example.Blog.Exception.ResourceNotFoundException;
import com.example.Blog.Service.UserService;
import com.example.Blog.payload.UserDto;
import com.example.Blog.repository.UserRepo;


@Service
public class UserServiceImpl implements UserService,UserDetailsService {
	
    @Autowired
	private UserRepo userRepo;
    
    @Autowired
    private ModelMapper modelMapper;
    
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           Optional<User> userInfo=userRepo.findByUserName(username);
		
           
           return userInfo.map(UserInfoDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not Found with username"+username));
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User createUser=this.userRepo.save(user);
		
		return this.userToDto(createUser);
		
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, int userId) {
		// TODO Auto-generated method stub
		User user= this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id", userId));
		
		user.setUserName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setRoles(userDto.getAbout());
		User updateduser=this.userRepo.save(user);
				 UserDto userdt2= this.userToDto(updateduser);
				 return userdt2;
	}

	@Override
	public UserDto getUserById(int userId) {
	
		User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id", userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDto= users.stream().map(user-> this.userToDto(user)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUser(int userId) {
		User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id", userId));
		
	this.userRepo.delete(user);
		
	}
	
	
	private User dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		
		
		return user;
		
		
		
	}
	private UserDto userToDto(User user) {
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		
		return userDto;
	}
	
	

}
