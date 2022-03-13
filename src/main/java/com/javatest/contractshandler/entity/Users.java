package com.javatest.contractshandler.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String surname;
	private UserTypes type;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Contracts> contracts = new HashSet<>();
	
	public Users() {
	}
	
	public Users(String name, String surname, UserTypes type) {
		this.name = name;
		this.surname = surname;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public UserTypes getType() {
		return type;
	}

	public void setType(UserTypes type) {
		this.type = type;
	}

	public Set<Contracts> getContracts() {
		return contracts;
	}

	public void setContracts(Set<Contracts> contracts) {
		this.contracts = contracts;
		
		contracts.forEach(c -> c.setUser(this));
	}

}
