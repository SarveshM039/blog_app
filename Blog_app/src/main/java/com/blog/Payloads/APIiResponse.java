package com.blog.Payloads;


public class APIiResponse {

	private String message;
	private boolean success;
	
	
	
	public APIiResponse(String message, boolean success) {
		this.message =" User deleted successfully";
		this.success = true;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
