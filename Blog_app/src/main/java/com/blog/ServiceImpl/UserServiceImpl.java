    package com.blog.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.Exceptions.*;
import com.blog.Entities.User;
import com.blog.Payloads.UserDto;
import com.blog.Repositories.UserRepo;
import com.blog.Services.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo ;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user= this.dtoToUser(userDto);
		User savedUser= this.userRepo.save(user);
		return this.userTODto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
	    
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		 UserDto userDto1 = this.userTODto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) { 
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		return this.userTODto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userdtos = users.stream().map(user->this.userTODto(user)).collect(Collectors.toList());
		return userdtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);
		
	}
	 
	
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		
		
		
		
	 /* user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout()); */
	
		return user;
	}
  
	public UserDto userTODto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
	/*	userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout()); */
		return userDto;
	}
}
