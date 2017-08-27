package com.comments;

public class Comment {

	private String author;
	private String message;
	
	public Comment(String author, String message) {
		this.author = author;
		this.message = message;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
