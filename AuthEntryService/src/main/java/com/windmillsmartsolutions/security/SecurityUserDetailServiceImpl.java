/**
 * 
 */
package com.windmillsmartsolutions.security;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.windmillsmartsolutions.dao.User;
import com.windmillsmartsolutions.dto.Response;
import com.windmillsmartsolutions.dto.UserDTO;
import com.windmillsmartsolutions.repository.UserRepository;
import com.windmillsmartsolutions.service.UserService;
import com.windmillsmartsolutions.utils.AuthEntryConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * Service to authenticate user
 * 
 * @author ravjagta
 *
 */
@Service
public class SecurityUserDetailServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	AuthEntryConstant appConstant;

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserDTO user = userService.findUserByUserName(userName);
		if (user == null || !user.getEnabled()) {
			throw new UsernameNotFoundException("User \"" + userName + "\" not found");
		}
		String roles = "ROLE_ADMIN";		
		return new SpringSecurityUser(user.getId(), user.getFirstName() + " " + user.getLastName(), user.getUsername(), user.getPassword(), user.getEmail(),
				user.getLastPasswordReset(),  AuthorityUtils.commaSeparatedStringToAuthorityList(roles));
	}

	/**
	 * @param authRequest
	 * @param request
	 * @return
	 */
	/*
	public Response<Object> authenticate(HttpServletRequest request,
			AuthenticationRequest authRequest) {
		// TODO Auto-generated method stub
		try {
			setAuthentication(authRequest);
			SpringSecurityUser userDetails = (SpringSecurityUser) loadUserByUsername(authRequest.getUsername());

			String token = this.tokenUtils.generateToken(userDetails);
			userDetails.setToken(token);

			// Login Success update login time
			captureLoginTime(userDetails.getId());
			userDetails.setPassword(null);
			return Response.ok().setPayload(userDetails);
		} catch (Exception e) {
			return Response.wrongCredentials("");
		}
		
	}

	private void setAuthentication(AuthenticationRequest authRequest) {
		Authentication authentication = this.authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private void captureLoginTime(Long id) {
		User user = userRepository.findById(id).orElse(null);
		user.setLastLogin(new Date());
		userRepository.save(user);
	}

	public ResponseEntity<AuthenticationResponse> refresh(HttpServletRequest request) {
		String token = request.getHeader(AuthEntryConstant.TOKEN_HEADER);
		String username = this.tokenUtils.getUsernameFromToken(token);
		SpringSecurityUser user = (SpringSecurityUser) loadUserByUsername(username);
		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
			String refreshToken = this.tokenUtils.refreshToken(token);
			return ResponseEntity.ok(new AuthenticationResponse(refreshToken));
		}
		return ResponseEntity.badRequest().body(null);
	}
	*/
}