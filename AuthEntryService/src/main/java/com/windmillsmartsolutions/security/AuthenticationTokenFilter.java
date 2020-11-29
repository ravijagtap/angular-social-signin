package com.windmillsmartsolutions.security;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.windmillsmartsolutions.utils.AuthEntryConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private GoogleTokenUtil googleTokenUtils;

	@Autowired
	private FacebookTokenUtil facebookTokenUtils;

	@Autowired
	private SecurityUserDetailServiceImpl authenticationService;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		tokenUtils = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext())
				.getBean(TokenUtils.class);

		authenticationService = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext())
				.getBean(SecurityUserDetailServiceImpl.class);

		HttpServletResponse resp = (HttpServletResponse) res;
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
		resp.setHeader("Access-Control-Max-Age", "10800");
		resp.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Request-With, TIMEZONE, Content-Type, Accept, PROVIDER" + AuthEntryConstant.TOKEN_HEADER);

		HttpServletRequest httpReq = (HttpServletRequest) req;
		String authToken = httpReq.getHeader(AuthEntryConstant.TOKEN_HEADER);
		String provider = httpReq.getHeader(AuthEntryConstant.PROVIDER);

		String email = null;
		try {
			if ("GOOGLE".equalsIgnoreCase(provider)) {
				email = this.googleTokenUtils.validateTokenAndReturnEmail(authToken);			
			} else {
				email = this.facebookTokenUtils.validateTokenAndReturnEmail(authToken);
			}
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}

		if(email != null) {
			//Get user from token and validate with database 
			try {
				UserDetails userDetails = this.authenticationService.loadUserByUsername(email);				
				if(userDetails != null) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpReq));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			} catch(UsernameNotFoundException e) {
			}
		}
			
		
		chain.doFilter(req, res);
	}
}

