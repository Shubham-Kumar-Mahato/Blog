package com.example.Blog.filter;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.Blog.Service.Impl.UserServiceImpl;
import com.example.Blog.Util.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@Component
public class JwtFilter extends OncePerRequestFilter{
	

	@Autowired
	private JwtUtils jwtService;
	
	
	
	@Autowired
	private UserServiceImpl userInfoService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader=request.getHeader("Authorization");
		String token =null;
		String userName=null;
		
		if(authHeader!=null && authHeader.startsWith("Bearer")){
			
			token=authHeader.substring(7);
			userName=jwtService.extractUsername(token);
			
		}
		
		if(userName !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=userInfoService.loadUserByUsername(userName);
			if(jwtService.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);
		
		// TODO Auto-generated method stub
		
	}

}
