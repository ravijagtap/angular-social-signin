package com.windmillsmartsolutions.security;

public class AuthenticationRequest {

	private String username;
	private String email;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;		
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public AuthenticationRequest() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		this.username = email;
	}
	
	public AuthenticationRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}
}
