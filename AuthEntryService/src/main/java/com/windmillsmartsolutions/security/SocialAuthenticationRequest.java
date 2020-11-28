package com.windmillsmartsolutions.security;

public class SocialAuthenticationRequest {

	private String username;
	private String token;
	private String socialId;
	private String name;
	private String email;
	private String userType;
	private String region;
	private String platform;
	
	public SocialAuthenticationRequest() {
		super();
	}
	
	public SocialAuthenticationRequest(String username, String token, String socialId, String name, String email, String userType, String region, String platform) {
		this.username = username;
		this.token = token;
		this.socialId = socialId;
		this.name = name;
		this.email = email;
		this.userType = userType;
		this.region = region;
		this.platform = platform;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
}
