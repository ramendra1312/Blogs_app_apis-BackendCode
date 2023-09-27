package com.turbo.blog.Security;

import java.io.IOException;
import java.nio.charset.MalformedInputException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		System.out.println("request");
		String requestToken = request.getHeader("Authorization");

		// Bearer 34234dsfs32dfsgf

		System.err.println(requestToken);

		String username = null;
		String token = null;
		if (requestToken != null && requestToken.startsWith("Bearer")) {

			token = requestToken.substring(7);

			try {
				username = this.jwtTokenHelper.getUsernameFromToken(token);
			} catch (IllegalArgumentException e) {
				// TODO: handle exception
				System.out.println("unable to get jwt token");
			} catch (ExpiredJwtException e) {
				// TODO: handle exception
				System.out.println("JWT  has expired ");
			} catch (MalformedJwtException e) {
				// TODO: handle exception
				System.out.println("Invalid jwt");
			}

		} else {
			System.out.println("Jwt token does not begins with Bearer");
		}
		// avi authenticate ni kr rha

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if (this.jwtTokenHelper.validateToken(token, userDetails)) {
				// working fine
				// authentication krna hai
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				System.err.println("invalid jwt token");

			}

		} else {
			System.out.println("username is null or context is not null");
		}
		
		filterChain.doFilter(request, response);
	

	}
//
//	public UserDetailsService getUserDetailsService() {
//		return userDetailsService;
//	}
//
//	public void setUserDetailsService(UserDetailsService userDetailsService) {
//		this.userDetailsService = userDetailsService;
//	}
//
//	public JwtTokenHelper getJwtTokenHelper() {
//		return jwtTokenHelper;
//	}
//
//	public void setJwtTokenHelper(JwtTokenHelper jwtTokenHelper) {
//		this.jwtTokenHelper = jwtTokenHelper;
//	}

}
