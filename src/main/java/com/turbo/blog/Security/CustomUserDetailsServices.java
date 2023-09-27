package com.turbo.blog.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.turbo.blog.entities.User;
import com.turbo.blog.exceptions.ResourcesNotFoundException;
import com.turbo.blog.repositories.UserRepo;


@Service
public class CustomUserDetailsServices  implements UserDetailsService{

	
	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.userRepo.findByemail(username).orElseThrow(()->new ResourcesNotFoundException("user", "email :"+username, 0));
		// TODO Auto-generated method stub
		return user;
	}

}
