package com.javatest.contractshandler.dao;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.javatest.contractshandler.entity.Contracts;

@Repository
public interface ContractsRepository extends JpaRepository<Contracts, Long>, JpaSpecificationExecutor<Contracts> {

	List<Contracts> findByUserId(Long userId);
	
	List<Contracts> findAll(Specification<Contracts> spec);
}
