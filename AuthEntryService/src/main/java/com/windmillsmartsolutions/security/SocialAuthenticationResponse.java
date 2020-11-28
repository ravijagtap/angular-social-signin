package com.windmillsmartsolutions.security;

public class SocialAuthenticationResponse {

	private String token;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public SocialAuthenticationResponse() {
		super();
	}
	
	public SocialAuthenticationResponse(String token) {
		this.setToken(token);
	}
}
