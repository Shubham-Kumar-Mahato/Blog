package com.example.Blog.payload;

public class CommentDto {

	
	private int commentId;
	private String content;
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
	public CommentDto(int commentId, String content) {
		super();
		this.commentId = commentId;
		this.content = content;
	}
	public CommentDto() {
	
	}
	
	
	
}
