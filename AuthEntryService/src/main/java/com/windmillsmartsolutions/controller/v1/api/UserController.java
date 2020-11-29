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

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
* Controller class for all User requests like fetc user details, or update profile.
*
* @author  Ravi Jagtap
* @version 1.0
*/
@RestController
public class UserController {

	@Autowired
    private UserService userService;
	
	@Autowired
	UserException userException;

	/**
	 * This method returns user detail based on email as input.
     * If user is not present in the application, then ENTITY_NOT_FOUND will be returned as response. 
	 * @param tokenMap
	 * @return Response
	 */  
	@PostMapping(value = "/fetchUser")
    public Response getUser(@RequestBody UserDTO userDTO) {				
		UserDTO user = null;		
		user = userService.findUserByUserName(userDTO.getEmail());
		if(user == null) {			
			return Response.notFound(userException.exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, userDTO.getEmail()));
		}
        return Response.ok().setPayload(user);
    }
	
	/**
	 * This method updates user profile.
     * If user is not present in the application, then ENTITY_NOT_FOUND will be returned as response. 
	 * @param tokenMap
	 * @return Response
	 */  
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
