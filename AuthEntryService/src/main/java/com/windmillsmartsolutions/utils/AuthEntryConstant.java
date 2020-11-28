package com.windmillsmartsolutions.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryConstant {

	public static final String TOKEN_HEADER = "X-AUTH-TOKEN";
	public static final String PROVIDER = "PROVIDER";
	public static final String GOOGLE = "google";
	
	@Value("${google.client.id}")
	public String GOOGLE_CLIENT_ID;	
}
