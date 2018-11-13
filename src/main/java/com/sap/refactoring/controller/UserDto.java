package com.sap.refactoring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.sap.refactoring.entity.User;
import com.sap.refactoring.entity.UserRole;

public class UserDto {
	
	@NotNull
	@Size(max = 100)
	private String name;
	
	@NotNull
	@Size(max = 100)
	private String email;
	
	private List<String> roles;
	
	private List<UserRole> lstRoles;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
		User tmpUsr=new User(name, email);
		lstRoles=new ArrayList<>();
		for(String s:roles) {
			lstRoles.add(new UserRole(s, tmpUsr));
		}
	}

	public List<UserRole> getLstRoles() {
		return lstRoles;
	}
	
	public UserDto() {
		
	}
	
	public UserDto(@NotNull @Size(max = 100) String name, @NotNull @Size(max = 100) String email, List<String> roles) {
		super();
		this.name = name;
		this.email = email;
		this.roles = roles;
	}
	
}
