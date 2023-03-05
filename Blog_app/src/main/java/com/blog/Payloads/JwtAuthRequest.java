package com.blog.Payloads;


import lombok.Data;


@Data
public class JwtAuthRequest {

	private String username;
	
	private String password;

	

	
}
