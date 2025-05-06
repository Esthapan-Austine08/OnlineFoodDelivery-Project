package com.nt.service;

import com.nt.model.User;

public interface UserService {

	public User findUserByJwtToken(String jwt) throws Exception;
	
	public User findUserByEmail(String email) throws Exception;
}
