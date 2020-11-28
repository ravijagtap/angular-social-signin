package com.windmillsmartsolutions.assembler;

import com.windmillsmartsolutions.dao.User;
import com.windmillsmartsolutions.dto.UserDTO;

/**
 * The class is to convert DTO to entity and vice-versa for the User entity.
 *  
 */
public class UserAssembler {
	
	/**
	 * Method to convert User entity to UserDTO
	 * @param user
	 * @return UserDTO
	 */
	public static UserDTO toUserDto(User user) {
		return new UserDTO()
				.setId(user.getId())
				.setEnabled(user.getEnabled())
				.setEmail(user.getEmail())
				.setFirstName(user.getFirstName())
				.setLastName(user.getLastName())				
				.setLastLogin(user.getLastLogin())
				.setLastPasswordReset(user.getLastPasswordReset())				
				.setUsername(user.getUsername())
				.setPassword(user.getPassword())
				.setCreatedDate(user.getCreatedDate())
				.setGender(user.getGender())				
				.setMobile(user.getMobile())
				.setAuthorities(user.getAuthorities());
				
    }
	
	/**
	 * Method to convert UserDTO to User entity 
	 * 
	 * @param userDTO
	 * @return user
	 */
	public static User toUser(UserDTO userDTO) {
		return new User()
				.setId(userDTO.getId())
				.setAddress(userDTO.getAddress())
				.setEmail(userDTO.getEmail())
				.setFirstName(userDTO.getFirstName())
				.setLastName(userDTO.getLastName())				
				.setEnabled(userDTO.getEnabled())
				.setLastLogin(userDTO.getLastLogin())
				.setLastPasswordReset(userDTO.getLastPasswordReset())
				.setUsername(userDTO.getEmail())
				.setPassword(userDTO.getPassword())
				.setCreatedDate(userDTO.getCreatedDate())
				.setGender(userDTO.getGender())
				.setMobile(userDTO.getMobile())
				.setAuthorities(userDTO.getAuthorities());
    }
	
}
