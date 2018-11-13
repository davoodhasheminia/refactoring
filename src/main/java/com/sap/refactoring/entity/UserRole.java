package com.sap.refactoring.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="userrole")
public class UserRole implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long roleId;
	
	@Column
	private String roleDesc;
	
	
    @ManyToOne(fetch=FetchType.LAZY)  
    @JoinColumn(name="userId",nullable=false)
    @JsonIgnore
	private User user;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public UserRole() {
		
	}
	
	public UserRole(String roleDesc) {
		
	}
	
	public UserRole(String roleDesc, User user) {		
		this.roleDesc = roleDesc;
		this.user = user;
	}

	public UserRole(Long roleId, String roleDesc, User user) {
		super();
		this.roleId = roleId;
		this.roleDesc = roleDesc;
		this.user = user;
	}
	
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return "roleId:" + getRoleId() + "," +
//		        "roleDesc:" + getRoleDesc();		        
//	}
	
//	@Override
//	public boolean equals(Object obj) {	
//		return ((UserRole) obj).getRoleDesc().equals(this.getRoleDesc());
//	}
//	
//	@Override
//	public int hashCode() {
//		// TODO Auto-generated method stub
//		return this.getRoleDesc().hashCode()+
//				this.getUser().hashCode();
//	}
	
	
}
