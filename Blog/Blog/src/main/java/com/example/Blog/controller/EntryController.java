package com.example.Blog.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Blog.Util.JwtUtils;
import com.example.Blog.payload.LoginRequest;






@RestController
@RequestMapping("/api")
public class EntryController {

	  @Autowired
	  private AuthenticationManager authenticationManager;
	
	 

	    @Autowired
	    private JwtUtils jwtUtils;
	
	    
	    @GetMapping("/home")
	    public String home() {
	    	return "welcome";
	    }
	
	    @PostMapping("/login")
	    public ResponseEntity<String> login(@RequestBody LoginRequest user) {
	    	Authentication authenticate=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			if(authenticate.isAuthenticated()) {
				String tokenString= jwtUtils.generateToken(user.getUsername());
				
				return new ResponseEntity<String>(tokenString,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<String>("Invalid username or password",HttpStatus.BAD_REQUEST);
			}
	    }
}
