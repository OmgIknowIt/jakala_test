package com.javatest.contractshandler.service;

import java.util.Optional;

import com.javatest.contractshandler.entity.Users;

public interface UsersService {

	Optional<Users> findById(Long id);
	
	Users saveUser(Users user);
}
