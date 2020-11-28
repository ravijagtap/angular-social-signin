package com.windmillsmartsolutions.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SecuritySuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private TokenUtils tokenUtils;
	 
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		SpringSecurityUser authUser = (SpringSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("username", authUser.getUsername());
        session.setAttribute("authorities", authentication.getAuthorities());
        
		//String token = this.tokenUtils.generateToken(authUser);
        //authUser.setToken(token);
        response.setCharacterEncoding("UTF-8");
        response.getOutputStream().print(
    			objectMapper.writeValueAsString(authUser));
        
        //set our response to OK status
        response.setStatus(HttpServletResponse.SC_OK);
 
	}
	 
}
