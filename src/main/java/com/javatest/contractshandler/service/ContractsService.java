package com.javatest.contractshandler.service;

import java.util.Date;
import java.util.List;

import com.javatest.contractshandler.entity.ContractTypes;
import com.javatest.contractshandler.entity.Contracts;
import com.javatest.contractshandler.entity.UserTypes;

public interface ContractsService {

	Contracts saveContract(Contracts contract);

	List<Contracts> findByUserId(Long userId);

	List<Contracts> findContractsBySearch(String userName, String userSurname, UserTypes userType, Date contractDate, ContractTypes contractTypes);
}
