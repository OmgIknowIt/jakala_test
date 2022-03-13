package com.javatest.contractshandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.javatest.contractshandler.dao.ContractsRepository;
import com.javatest.contractshandler.dao.UsersRepository;
import com.javatest.contractshandler.dao.filters.SearchSpecification;
import com.javatest.contractshandler.entity.ContractTypes;
import com.javatest.contractshandler.entity.Contracts;
import com.javatest.contractshandler.entity.UserTypes;
import com.javatest.contractshandler.entity.Users;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchTest {

	@Autowired
	private ContractsRepository contractsRepo;
	@Autowired
	private UsersRepository usersRepo;
	
	private Users user1;
	private Users user2;
	private Contracts contract1;
	private Contracts contract2;
	private Contracts contract3;
	
	@BeforeEach
	void init() {
		
		user1 = new Users("John", "Doe", UserTypes.BUSINESS_USER);
		contract1 = new Contracts(ContractTypes.ELECTRICITY, new Date());
		contract2 = new Contracts(ContractTypes.GAS, new Date());
		
		user1.setContracts(Collections.singleton(contract1));
		user1.setContracts(Collections.singleton(contract2));
		
		user2 = new Users("Jane", "Doe", UserTypes.PRIVATE_USER);
		contract3 = new Contracts(ContractTypes.GAS_AND_ELECTRICITY, new Date());
		
		user2.setContracts(Collections.singleton(contract3));
		
		usersRepo.save(user1);
		usersRepo.save(user2);
	}
	
	@AfterEach
	void tierDown() {
		usersRepo.delete(user1);
		usersRepo.delete(user2);
	}
	
	@Test
	void findContractsByUserName() {
		List<Contracts> contracts = contractsRepo.findAll(SearchSpecification.likeUserName(user1.getName()));
		assertNotNull(contracts);
	}
	
	@Test
	void findContractsByDate() {
		List<Contracts> contracts = contractsRepo.findAll(SearchSpecification.whereContractDate(contract2.getDate()));
		assertNotNull(contracts);
	}
	
	@Test
	void findContractsByContractType() {
		List<Contracts> contracts = contractsRepo.findAll(SearchSpecification.whereContractType(contract2.getType()));
		assertNotNull(contracts);
		assertEquals(1, contracts.size());
		assertEquals(contract2.getType(), contracts.get(0).getType());
	}
	
	@Test
	void findContractsByUserType() {
		List<Contracts> contracts = contractsRepo.findAll(SearchSpecification.whereUserType(user2.getType()));
		assertNotNull(contracts);
	}
}
