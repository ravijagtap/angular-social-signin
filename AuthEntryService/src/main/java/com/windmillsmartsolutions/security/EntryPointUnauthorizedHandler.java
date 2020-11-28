package com.windmillsmartsolutions.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component(value = "authenticationEntryPoint")
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		System.out.println("**********Access Deinied");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
		
	}

}
