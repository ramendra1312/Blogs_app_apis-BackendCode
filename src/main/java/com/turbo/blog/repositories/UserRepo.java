package com.turbo.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turbo.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	Optional<User> findById(User userId);
	
	Optional<User>findByemail(String email);

}
