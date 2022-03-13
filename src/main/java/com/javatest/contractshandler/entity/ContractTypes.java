package com.javatest.contractshandler.entity;

import java.util.stream.Stream;

public enum ContractTypes {
	GAS_AND_ELECTRICITY(0),
	GAS(1),
	ELECTRICITY(2);
	
	private Integer contractType;

	private ContractTypes(Integer contractType) {
		this.contractType = contractType;
	}

	public Integer getContractType() {
		return contractType;
	}
	
    public static ContractTypes of(Integer type) {
        return Stream.of(ContractTypes.values())
          .filter(t -> t.getContractType().equals(type))
          .findFirst()
          .orElse(null);
    }
}
