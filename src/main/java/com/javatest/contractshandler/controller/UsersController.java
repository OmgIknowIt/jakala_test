package com.javatest.contractshandler.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatest.contractshandler.entity.Users;
import com.javatest.contractshandler.service.UsersService;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	private UsersService usersService;

	@Autowired
	public UsersController(UsersService usersService) {
		this.usersService = usersService;
	}
	
	@PostMapping
	public ResponseEntity<Users> saveUser(@RequestBody Users user) {
		try {
			Users saved = usersService.saveUser(user);
			return new ResponseEntity<>(saved, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Users> getById(@PathVariable Long id) {
		Optional<Users> optionalUser = usersService.findById(id);
		if (!optionalUser.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
	}
}
