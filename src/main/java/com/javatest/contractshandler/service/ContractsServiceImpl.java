package com.javatest.contractshandler.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.javatest.contractshandler.dao.ContractsRepository;
import com.javatest.contractshandler.dao.filters.SearchSpecification;
import com.javatest.contractshandler.entity.ContractTypes;
import com.javatest.contractshandler.entity.Contracts;
import com.javatest.contractshandler.entity.UserTypes;

@Service
public class ContractsServiceImpl implements ContractsService {

	private ContractsRepository contractsRepository;

	@Autowired
	public ContractsServiceImpl(ContractsRepository contractsRepository) {
		this.contractsRepository = contractsRepository;
	}

	@Override
	public Contracts saveContract(Contracts contract) {
		return contractsRepository.save(contract);
	}

	@Override
	public List<Contracts> findByUserId(Long userId) {
		return contractsRepository.findByUserId(userId);
	}

	@Override
	public List<Contracts> findContractsBySearch(String userName, String userSurname, UserTypes userType,
			Date contractDate, ContractTypes contractType) {

		Specification<Contracts> spec = null;

		if (userName != null) {
			spec = addNewSpec(spec, SearchSpecification.likeUserName(userName));
		}
		if (userSurname != null) {
			spec = addNewSpec(spec, SearchSpecification.likeUserSurname(userSurname));
		}
		if (userType != null) {
			spec = addNewSpec(spec, SearchSpecification.whereUserType(userType));
		}
		if (contractDate != null) {
			spec = addNewSpec(spec, SearchSpecification.whereContractDate(contractDate));
		}
		if (contractType != null) {
			spec = addNewSpec(spec, SearchSpecification.whereContractType(contractType));
		}

		return contractsRepository.findAll(spec);
	}

	private Specification<Contracts> addNewSpec(Specification<Contracts> spec, Specification<Contracts> newSpec) {
		if (spec != null) {
			return spec.and(newSpec);
		} 
		return newSpec;
	}
}
