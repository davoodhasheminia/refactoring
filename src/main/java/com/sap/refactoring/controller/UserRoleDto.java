package com.sap.refactoring.controller;

import java.util.ArrayList;
import java.util.List;

import com.sap.refactoring.entity.User;
import com.sap.refactoring.entity.UserRole;

public class UserRoleDto {
	
	private List<String> roles;
	private List<UserRole> lstRoles;
	
	public List<UserRole> getLstRoles() {
		return lstRoles;
	}

	public void setLstRoles(List<UserRole> lstRoles) {
		this.lstRoles = lstRoles;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;	
		lstRoles=new ArrayList<>();
		for(String s:roles) {
			lstRoles.add(new UserRole(s));
		}
	}
	
	public UserRoleDto() {
		
	}
	

	public UserRoleDto(List<String> roles) {
		super();
		setRoles(roles);
	}	
	
}
