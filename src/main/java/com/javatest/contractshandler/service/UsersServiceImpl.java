package com.javatest.contractshandler.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatest.contractshandler.dao.UsersRepository;
import com.javatest.contractshandler.entity.Users;

@Service
public class UsersServiceImpl implements UsersService {

	
	private UsersRepository personRepository;
	
	@Autowired
	public UsersServiceImpl(UsersRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public Optional<Users> findById(Long id) {
		return personRepository.findById(id);
	}

	@Override
	public Users saveUser(Users user) {
		return personRepository.save(user);
	}
}
