package com.javatest.contractshandler.dao.filters;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

import com.javatest.contractshandler.entity.ContractTypes;
import com.javatest.contractshandler.entity.Contracts;
import com.javatest.contractshandler.entity.UserTypes;
import com.javatest.contractshandler.entity.metamodels.Contracts_;
import com.javatest.contractshandler.entity.metamodels.Users_;

public class SearchSpecification {

	public static Specification<Contracts> likeUserName(String name) {
		return (root, query, builder) -> {
			return builder.like(root.get(Contracts_.USER).get(Users_.NAME), "%" + name + "%");
		};
	}

	public static Specification<Contracts> likeUserSurname(String surname) {
		return (root, query, builder) -> {
			return builder.like(root.get(Users_.SURNAME), "%" + surname + "%");
		};
	}

	public static Specification<Contracts> whereUserType(UserTypes userType) {
		return (root, query, builder) -> {
			return builder.equal(root.get(Users_.TYPE), userType.getUserType());
		};
	}

	public static Specification<Contracts> whereContractDate(Date date) {
		return (root, query, builder) -> {
			return builder.equal(root.get(Contracts_.DATE), date);
		};
	}

	public static Specification<Contracts> whereContractType(ContractTypes contractType) {
		return (root, query, builder) -> {
			return builder.equal(root.get(Contracts_.TYPE), contractType.getContractType());
		};
	}
}
