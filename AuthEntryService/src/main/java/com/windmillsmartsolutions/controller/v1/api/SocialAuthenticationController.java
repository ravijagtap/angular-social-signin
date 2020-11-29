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
import com.windmillsmartsolutions.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
* Controller class to hanle all requests related to Social Sign-in and Token Verification
*
* @author  Ravi Jagtap
* @version 1.0
*/
@RestController
public class SocialAuthenticationController {

    @Autowired
    GoogleTokenUtil googleToken;

    @Autowired
    UserService userService;

    @Autowired
    UserException userException;
    
    /**
	 * This method is called during the sign-in process. This validates the token prvided by google or facebook and fetch the user details.
     * If Token is not valid then UNAUTHORIZED_ACCESS will be returned as response 
     * If user is not registered in the application, then ENTITY_NOT_FOUND will be returned as response. 
	 * @param tokenMap
	 * @return Response
	 */
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

    /**
	 * This is called during the sign-up process. This validates the token prvided by google or facebook and fetch the user details.
     * If Token is not valid then UNAUTHORIZED_ACCESS will be returned as response 
     * If user is already registered in the application, then DUPLICATE_ENTITY will be returned as response. 
	 * @param tokenMap
	 * @return Response
	 */    
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
