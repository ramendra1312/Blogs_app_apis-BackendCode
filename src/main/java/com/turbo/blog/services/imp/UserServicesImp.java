package com.turbo.blog.services.imp;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turbo.blog.entities.User;
import com.turbo.blog.exceptions.ResourcesNotFoundException;
import com.turbo.blog.payloads.UserDto;
import com.turbo.blog.repositories.UserRepo;
import com.turbo.blog.services.UserService;

@Service
public class UserServicesImp implements UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {

		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto upateUser(UserDto userDto, Integer userID) {
		User user = this.userRepo.findById(userID)
				.orElseThrow(() -> new ResourcesNotFoundException("user", "id", userID));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userID) {
		User user = this.userRepo.findById(userID)
				.orElseThrow(() -> new ResourcesNotFoundException("user", "id", userID));

		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourcesNotFoundException("user","Id",userId));
		this.userRepo.delete(user);
		
	}

	public User dtoToUser(UserDto userDto) {
		User user =this.modelMapper.map(userDto, User.class);
		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setPassword(userDto.getPassword());
		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto =this.modelMapper.map(user, UserDto.class);
		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		return userDto;
	}

}
