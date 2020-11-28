package com.windmillsmartsolutions.security;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.windmillsmartsolutions.utils.AuthEntryConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoogleTokenUtil {

    @Autowired
	AuthEntryConstant appConstant;
	
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	
	private static HttpTransport httpTransport;
	
	public String validateTokenAndReturnEmail(String idTokenString) throws GeneralSecurityException, IOException {		
		httpTransport = GoogleNetHttpTransport.newTrustedTransport();		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, JSON_FACTORY)
				.setAudience(Collections.singletonList(appConstant.GOOGLE_CLIENT_ID))				
				.build();

		GoogleIdToken idToken = verifier.verify(idTokenString);
		if(idToken != null) {
			Payload payload = idToken.getPayload();

            String userId = payload.getSubject();

			String email = payload.getEmail();
			
			boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());

            String name = (String) payload.get("name");
			
			String pictureUrl = (String) payload.get("picture");
            
            String locale = (String) payload.get("locale");
			
			String familyName = (String) payload.get("family_name");
			
			String givenName = (String) payload.get("given_name");
			
			return email;			
		} else {			
			return null;
		}

	}
    
}
