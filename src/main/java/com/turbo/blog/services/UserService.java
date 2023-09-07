package com.turbo.blog.services;

import java.util.List;

import com.turbo.blog.entities.User;
import com.turbo.blog.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);

	UserDto upateUser(UserDto user, Integer userID);

	UserDto getUserById(Integer userID);

	List<UserDto> getAllUsers();

	void deleteUser(Integer userId);
}
