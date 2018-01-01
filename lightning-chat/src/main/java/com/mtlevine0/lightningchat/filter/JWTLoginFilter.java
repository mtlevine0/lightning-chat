package com.mtlevine0.lightningchat.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtlevine0.lightningchat.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	public JWTLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authManager) {
		super(defaultFilterProcessesUrl);
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		
		User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
		
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						user.getUsername(), 
						user.getPassword(), 
						Collections.emptyList()
					));
	}
	
	@Override
	protected void successfulAuthentication(
			HttpServletRequest req,
			HttpServletResponse res,
			FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		
		String jwt = Jwts.builder()
		  .setSubject(auth.getName())
		  .claim("roles", auth.getAuthorities())
		  .signWith(SignatureAlgorithm.HS256, "secret")
		  .compact();
		
		res.addHeader("Authorization", jwt);
		
	}

}
