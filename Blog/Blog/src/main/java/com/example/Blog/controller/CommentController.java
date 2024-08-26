package com.example.Blog.controller;

import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Blog.Service.CommentService;
import com.example.Blog.payload.CommentDto;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

	
	@Autowired
	private CommentService commentService;
	
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable int postId){
		CommentDto commentDto2 =this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(commentDto2,HttpStatus.CREATED);
	}
	public BodyBuilder deleteComment(@PathVariable int commentId){
		this.commentService.deleteComment(commentId);
		return  ResponseEntity.status(HttpStatus.OK);
	}
}
