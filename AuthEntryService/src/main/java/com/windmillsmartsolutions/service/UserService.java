package com.windmillsmartsolutions.service;

import java.util.Map;

import com.windmillsmartsolutions.dto.UserDTO;
import com.windmillsmartsolutions.exception.UserException;

public interface UserService {

	UserDTO signup(UserDTO userDto)  throws UserException;
	UserDTO findUserByEmail(String email)  throws UserException;
	UserDTO findUserByUserName(String userName)  throws UserException;	
	UserDTO changePassword(UserDTO userDto, String newPassword) throws UserException;
	UserDTO updateProfile(UserDTO userDTO) throws UserException;
	UserDTO signup(Map<String, String> tokenMap) throws UserException;
}
