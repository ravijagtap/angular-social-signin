/**
 * 
 */
package com.windmillsmartsolutions.controller.v1.api;

import com.windmillsmartsolutions.dto.Response;
import com.windmillsmartsolutions.dto.UserDTO;
import com.windmillsmartsolutions.exception.EntityType;
import com.windmillsmartsolutions.exception.ExceptionType;
import com.windmillsmartsolutions.exception.UserException;
import com.windmillsmartsolutions.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ravjagta
 *
 */
@RestController
public class UserController {

	@Autowired
    private UserService userService;
	
	@Autowired
	UserException userException;

	@PostMapping(value = "/signup")
    public Response signup(@RequestBody UserDTO userDTO) {		
		String username = userDTO.getUsername();
		UserDTO user = userService.findUserByEmail(userDTO.getEmail());
		if(user == null) {			
			return Response.duplicateEntity(userException.exception(EntityType.USER, ExceptionType.DUPLICATE_ENTITY, username));
		} else {			
			userDTO = userService.signup(userDTO);    
			return Response.ok().setPayload(userDTO);
		}	
    }
	
	@GetMapping(value = "/user/{username}")
    public Response getUser(@PathVariable("username") String username) {
		UserDTO userDTO = null;		
		userDTO = userService.findUserByUserName(username);
		if(userDTO == null) {			
			return Response.notFound(userException.exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, username));
		}
        return Response.ok().setPayload(userDTO);
	}
	
	@PostMapping(value = "/fetchUser")
    public Response getUser(@RequestBody UserDTO userDTO) {				
		UserDTO user = null;		
		user = userService.findUserByUserName(userDTO.getEmail());
		if(user == null) {			
			return Response.notFound(userException.exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, userDTO.getEmail()));
		}
        return Response.ok().setPayload(user);
    }
	
	
	@PostMapping(value = "/user")
    public Response updateProfile(@RequestBody UserDTO userDTO) {		
		try {
			userDTO = userService.updateProfile(userDTO);
		} catch (UserException e) {
			return Response.notFound(userException.exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, userDTO.getUsername()));
		}        
        return Response.ok().setPayload(userDTO);
	}
}
