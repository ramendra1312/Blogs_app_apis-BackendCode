package com.turbo.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turbo.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
