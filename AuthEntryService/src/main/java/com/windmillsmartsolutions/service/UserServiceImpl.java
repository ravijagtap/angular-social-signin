package com.windmillsmartsolutions.service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import com.windmillsmartsolutions.assembler.UserAssembler;
import com.windmillsmartsolutions.dao.User;
import com.windmillsmartsolutions.dto.UserDTO;
import com.windmillsmartsolutions.exception.UserException;
import com.windmillsmartsolutions.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * Service class for User Entity
 * @author ravjagta
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserException userException;

	/**
	 * Sign-up a user if its not already present
	 * @param userDto
	 * @return userDto
	 */
	@Override
	public UserDTO signup(UserDTO userDto) {
		Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
		if (user.isPresent()) {
			return null;
		} else {
			userDto.setCreatedDate(new Date());	
			userDto.setLastLogin(new Date());	
			userDto.setAuthorities("ROLE_ADMIN");
			userDto.setEnabled(true);
			userDto.setPassword((new BCryptPasswordEncoder()).encode(userDto.getPassword()));
			return UserAssembler.toUserDto(userRepository.save(UserAssembler.toUser(userDto)));
		}
	}

	@Override
	public UserDTO signup(Map<String, String> tokenMap) {
		UserDTO userDto = new UserDTO();
		userDto.setFirstName(tokenMap.get("firstName"));
		userDto.setLastName(tokenMap.get("lastName"));
		userDto.setUsername(tokenMap.get("email"));
		userDto.setEmail(tokenMap.get("email"));
		userDto.setAuthorities("ROLE_ADMIN");	
		userDto.setEnabled(true);
		userDto.setCreatedDate(new Date());	
		userDto.setLastLogin(new Date());				
		return UserAssembler.toUserDto(userRepository.save(UserAssembler.toUser(userDto)));
	}

	/**
	 * Update password of a user
	 * 
	 * @param userDto
	 * @param newPassword
	 * @return userDTO
	 */
	@Override
	public UserDTO changePassword(UserDTO userDto, String newPassword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Find user by email
	 * 
	 * @param email
	 * @return userDTO
	 */
	@Override
	public UserDTO findUserByEmail(String email) {
		Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
		if (user.isPresent()) {			
			return UserAssembler.toUserDto(user.get());
		}
		//throw userException.exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, email);
		return null;
	}
	
	@Override
	public UserDTO findUserByUserName(String userName) {
		Optional<User> user = Optional.ofNullable(userRepository.findByUsername(userName));
		if (user.isPresent()) {			
			return UserAssembler.toUserDto(user.get());
		}
		return null;
	}

	@Override
	public UserDTO updateProfile(UserDTO userDTO) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
