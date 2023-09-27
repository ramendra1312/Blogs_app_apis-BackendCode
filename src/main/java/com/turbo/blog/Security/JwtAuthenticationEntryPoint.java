package com.turbo.blog.Security;

import java.io.IOException;



import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;


@Component
@Data
public class JwtAuthenticationEntryPoint    implements  AuthenticationEntryPoint {

	//when api secured  ,unauthroied person try to access these api ,with we can give the error
	//respone error
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Request is denined");
		
	}
	

}
