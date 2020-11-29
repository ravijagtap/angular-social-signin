package com.windmillsmartsolutions.security;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

import com.windmillsmartsolutions.utils.AuthEntryConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FacebookTokenUtil {
   
    @Autowired
    AuthEntryConstant appConstant;
    
    private static final String GRAPH_API_BASE_URL = "https://graph.facebook.com/me?fields=email&access_token=";

    public String validateTokenAndReturnEmail(String idTokenString) throws GeneralSecurityException, IOException {		        

        RestTemplate restTemplate = new RestTemplate();  
        String url = GRAPH_API_BASE_URL + idTokenString;        
        System.out.println("**********url************"+url); 
        //Map res = this.restTemplate.postForObject(uri, getHttpEntity(dto), Map.class);
        ResponseEntity<Map> response =  restTemplate.exchange(url, HttpMethod.POST, null, Map.class);        
        if(response == null) {
            return null;
        }
        return (String)response.getBody().get("email");
    }

    /*private HttpEntity getHttpEntity(RequestDTO dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + getBase64Credentials());        
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
            
        return new HttpEntity<>(dto, headers);
    }*/
}
