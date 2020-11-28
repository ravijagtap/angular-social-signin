package com.windmillsmartsolutions.controller.v1.api;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

import com.windmillsmartsolutions.dto.Response;
import com.windmillsmartsolutions.dto.UserDTO;
import com.windmillsmartsolutions.exception.EntityType;
import com.windmillsmartsolutions.exception.ExceptionType;
import com.windmillsmartsolutions.exception.UserException;
import com.windmillsmartsolutions.security.GoogleTokenUtil;
import com.windmillsmartsolutions.security.SecurityUserDetailServiceImpl;
import com.windmillsmartsolutions.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocialAuthenticationController {

    @Autowired
    GoogleTokenUtil googleToken;

    @Autowired
    UserService userService;

    @Autowired
    UserException userException;
    


    @PostMapping(value = "/v1/validateTokenAndGetUserDetails")
    public Response validateTokenAndGetUserDetails(@RequestBody Map tokenMap) {
        try {
            String email = googleToken.validateTokenAndReturnEmail((String)tokenMap.get("idToken"));
            if (email != null) {
                UserDTO userDTO = userService.findUserByEmail((String)tokenMap.get("email"));
                if (userDTO != null) {
                    return Response.ok().setPayload(userDTO);
                } else {
                    return Response.notFound(userException.exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, email));
                }
            } else {
                return Response.unauthorized(userException.exception(EntityType.USER, ExceptionType.UNAUTHORIZED_ACCESS, email));
            }
        } catch (UserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Response.unauthorized(userException.exception(EntityType.USER, ExceptionType.UNAUTHORIZED_ACCESS, ""));
    }
    
    @PostMapping(value = "/v1/validateTokenAndSignUp")
    public Response validateTokenAndSignUp(@RequestBody Map tokenMap) {
        try {
            String email = googleToken.validateTokenAndReturnEmail((String)tokenMap.get("idToken"));
            if (email != null) {
                UserDTO userDTO = userService.findUserByEmail((String)tokenMap.get("email"));
                if (userDTO == null) {
                    userDTO = userService.signup(tokenMap);
                    return Response.ok().setPayload(userDTO);
                } else {
                    return Response.duplicateEntity(userException.exception(EntityType.USER, ExceptionType.DUPLICATE_ENTITY, email));
                }
            } else {
                return Response.unauthorized(userException.exception(EntityType.USER, ExceptionType.UNAUTHORIZED_ACCESS, email));
            }
        } catch (UserException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.unauthorized(userException.exception(EntityType.USER, ExceptionType.UNAUTHORIZED_ACCESS, ""));
	}
}
