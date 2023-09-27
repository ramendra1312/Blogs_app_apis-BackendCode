//package com.turbo.blog;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@SpringBootApplication
//public class BlogAppApisApplication  implements CommandLineRunner {
//	
//	
//	private PasswordEncoder passwordEncoder;
//
//	public static void main(String[] args) {
//		SpringApplication.run(BlogAppApisApplication.class, args);
//		
//		
//	
//	}
//	@Bean
//	public ModelMapper modelMapper() {
//		return new ModelMapper();
//	}
//	@Override
//	public void run(String... args) throws Exception {
//		
//		System.out.println(this.passwordEncoder.encode("ABCD"));
//	}
//
//}
package com.turbo.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;

    public BlogAppApisApplication(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogAppApisApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.passwordEncoder.encode("xyz"));
    }

}
