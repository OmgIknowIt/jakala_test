package com.javatest.contractshandler.entity.metamodels;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.javatest.contractshandler.entity.Contracts;
import com.javatest.contractshandler.entity.UserTypes;
import com.javatest.contractshandler.entity.Users;

@StaticMetamodel(Users.class)
public class Users_ {
	public static volatile SingularAttribute<Users, Long> id;

	public static volatile SingularAttribute<Users, String> name;

	public static volatile SingularAttribute<Users, String> surname;

	public static volatile SingularAttribute<Users, UserTypes> type;

	public static volatile SetAttribute<Users, Contracts> contracts;

	public static final String ID = "id";
	
	public static final String NAME = "name";
	
	public static final String SURNAME = "surname";
	
	public static final String TYPE = "type";
	
	public static final String CONTRACTS = "contracts";

}
