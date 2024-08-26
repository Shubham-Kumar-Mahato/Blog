package com.example.Blog.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentId;
	
	private String content;
	
	@ManyToOne
	private Post post;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Comment(int commentId, String content, Post post) {
		super();
		this.commentId = commentId;
		this.content = content;
		this.post = post;
	}

	public Comment() {
		
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", content=" + content + ", post=" + post + "]";
	}
	
	
	
	
	
	
}
