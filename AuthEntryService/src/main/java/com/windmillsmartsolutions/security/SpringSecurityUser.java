package com.windmillsmartsolutions.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class SpringSecurityUser implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7704708671900877484L;
	private Long id;
	private String username;
	private String password;
	private String name;
	private String email;
	private Date lastPasswordReset;
	private Collection<? extends GrantedAuthority> authorities;
	private Boolean accountNotExpired = true;
	private Boolean accountNotLocked = true;
	private Boolean credentialsNotExpired = true;
	private Boolean enabled = true;
	private String token = null;
	private String personId = null;
	private String currency = null;
	
	public SpringSecurityUser() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public SpringSecurityUser (Long id, String name, String username, String password, String email, Date lastPasswordReset, Collection<? extends GrantedAuthority> authorities) {
		setId(id);
		setName(name);
		setUsername(username);
		setPassword(password);
		setEmail(email);
		setLastPasswordReset(lastPasswordReset);
		setAuthorities(authorities);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastPasswordReset() {
		return lastPasswordReset;
	}

	public void setLastPasswordReset(Date lastPasswordReset) {
		this.lastPasswordReset = lastPasswordReset;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Boolean getAccountNotExpired() {
		return accountNotExpired;
	}

	public void setAccountNotExpired(Boolean accountNotExpired) {
		this.accountNotExpired = accountNotExpired;
	}

	public Boolean getAccountNotLocked() {
		return accountNotLocked;
	}

	public void setAccountNotLocked(Boolean accountNotLocked) {
		this.accountNotLocked = accountNotLocked;
	}

	public Boolean getCredentialsNotExpired() {
		return credentialsNotExpired;
	}

	public void setCredentialsNotExpired(Boolean credentialsNotExpired) {
		this.credentialsNotExpired = credentialsNotExpired;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return getAccountNotExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return getAccountNotLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return getCredentialsNotExpired();
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return getEnabled();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
