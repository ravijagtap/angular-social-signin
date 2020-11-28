package com.windmillsmartsolutions.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String address;

	private Date createdDate;

	private String email;

	private String firstName;

	private String lastName;

	private String password;

	private String mobile;

	private String gender;

	private Boolean enabled;

	private Date updatedDate;

	private String authorities;

	private String username;

	private Date lastPasswordReset;

	private Date lastLogin;
}