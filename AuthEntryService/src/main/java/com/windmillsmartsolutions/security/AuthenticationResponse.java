package com.windmillsmartsolutions.security;

public class AuthenticationResponse {

	private String token;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public AuthenticationResponse() {
		super();
	}
	
	public AuthenticationResponse(String token) {
		this.setToken(token);
	}
}
