package com.javatest.contractshandler.entity.metamodels;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.javatest.contractshandler.entity.ContractTypes;
import com.javatest.contractshandler.entity.Contracts;
import com.javatest.contractshandler.entity.Users;

@StaticMetamodel(Contracts.class)
public class Contracts_ {
    public static volatile SingularAttribute<Contracts, Long> id;

    public static volatile SingularAttribute<Contracts, ContractTypes> type;

    public static volatile SingularAttribute<Contracts, Date> date;

    public static volatile SingularAttribute<Contracts, Users> user;
    
	public static final String ID = "id";
	
	public static final String TYPE = "type";
	
	public static final String DATE = "date";
	
	public static final String USER = "user";
}
