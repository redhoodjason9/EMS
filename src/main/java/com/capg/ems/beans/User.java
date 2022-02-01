package com.capg.ems.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class User {

	@Id
	private String username;
	
	
	private String password;
	
	private String role;
	
	@Transient
	private String jwt;

	public User() {
		super();
	}

	public User(String username, String password, String jwt, String role) {
		super();
		this.username = username;
		this.password = password;
		this.jwt = jwt;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
