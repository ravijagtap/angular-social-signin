package com.windmillsmartsolutions.repository;

import java.util.List;
import java.util.Set;

import com.windmillsmartsolutions.dao.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
	Set<User> findByAuthoritiesIn(List<String> authorities);
	User findByEmail(String email);	
}
