package com.javatest.contractshandler.entity;

import java.util.stream.Stream;

public enum UserTypes {
	PRIVATE_USER(0),
	BUSINESS_USER(1);
	
	private Integer userType;

	private UserTypes(Integer userType) {
		this.userType = userType;
	}

	public Integer getUserType() {
		return userType;
	}
	
    public static UserTypes of(Integer type) {
        return Stream.of(UserTypes.values())
          .filter(t -> t.getUserType().equals(type))
          .findFirst()
          .orElse(null);
    }
}
