package com.turbo.blog.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
	
	private int id;
	@NotEmpty
	@Size(min = 4,message = "username must be min 4 waords")
	private String name;
	@NotEmpty
	@Email(message = "email is not valid ,kindly give a valid email")
	private String email;
	@NotEmpty
	@Size(min = 3,max = 10, message = "password must be of 3 char nd max of 10 char ")
	private String password;
	@NotEmpty
	private String about;
	

}
