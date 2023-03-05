package com.blog.Services;

import java.util.List;

import com.blog.Payloads.UserDto;

public interface UserService {
 
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto getUserById(Integer user);

       List<UserDto> getAllUsers();
       
       void deleteUser(Integer userId);
}
