package com.javatest.contractshandler.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javatest.contractshandler.entity.ContractTypes;
import com.javatest.contractshandler.entity.Contracts;
import com.javatest.contractshandler.entity.UserTypes;
import com.javatest.contractshandler.entity.Users;
import com.javatest.contractshandler.service.ContractsService;
import com.javatest.contractshandler.service.UsersService;

@RestController
@RequestMapping("/api/contracts")
public class ContractsController {

	private ContractsService contractsService;
	private UsersService usersService;

	@Autowired
	public ContractsController(ContractsService contractsService, UsersService usersService) {
		this.contractsService = contractsService;
		this.usersService = usersService;
	}

	@PostMapping
	public ResponseEntity<Contracts> saveContract(@RequestBody Contracts contract) {
		Optional<Users> optionalUser = usersService.findById(contract.getUser().getId());
		if (!optionalUser.isPresent()) {
			return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		contract.setUser(optionalUser.get());

		Contracts savedContract = contractsService.saveContract(contract);

		return new ResponseEntity<>(savedContract, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Contracts>> findContractsByUser(@RequestParam("user_id") Long id) {
		List<Contracts> listOfContracts = contractsService.findByUserId(id);
		if (listOfContracts.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listOfContracts, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<List<Contracts>> findContractsBySearch(
			@RequestParam(name = "user_name", required = false) String userName,
			@RequestParam(name = "user_surname", required = false) String userSurname,
			@RequestParam(name = "user_type", required = false) Integer userType,
			@RequestParam(name = "contract_date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date contractDate,
			@RequestParam(name = "contract_type", required = false) Integer contractType
			) {
		List<Contracts> listOfContracts = contractsService.findContractsBySearch(userName, userSurname, UserTypes.of(userType), contractDate, ContractTypes.of(contractType));
		if (listOfContracts.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(listOfContracts, HttpStatus.OK);
	}
}
