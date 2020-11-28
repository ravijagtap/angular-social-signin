package com.windmillsmartsolutions.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author ravjagta
 * The persistent class for the USER database table.
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "USER")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "MOBILE")
	private String mobile;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "ENABLED")
	private Boolean enabled; 
	
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm aaa")
	@Column(name = "CREATED_DATE")
	private Date createdDate;	
	
	@Column(name = "last_loggedin_time")
	private Date lastLogin;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "lastpasswordreset")
	private Date lastPasswordReset;

	@Column(name = "AUTHORITIES")
	private String authorities;
}