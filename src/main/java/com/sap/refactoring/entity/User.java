package com.sap.refactoring.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;
//import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name="user")
@XmlRootElement
public class User implements Serializable{
	
	public User(Long userId, String name, String email, List<UserRole> userroles) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.userroles = userroles;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Long userId;
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column
	private String name;
	
	@Column
	private String email;
	
	@OneToMany(mappedBy = "user",	
			   fetch = FetchType.EAGER, 
			   cascade = CascadeType.ALL)	  
	private List<UserRole> userroles=new ArrayList<>();
	
	
	
    public Long getUserId() {
		return userId;
	}
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
    
    public List<UserRole> getRoles() {
        return userroles;
    }
    
    public void setRoles(List<UserRole> roles) {
        this.userroles = roles;
    }
    
    
    public void addUserRole(UserRole userRole) {
        userroles.add(userRole);
        userRole.setUser(this);
    }
 
    public void removeComment(UserRole userRole) {
    	userroles.remove(userRole);
    	userRole.setUser(null);
    }
    
    public User() {
    	
    }
    
	public User(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	public User(String name, String email, List<UserRole> roles) {
		super();
		this.name = name;
		this.email = email;
		this.userroles = roles;
	}
    
	
	public User(List<String> roles,String name, String email ) {
		
		List<UserRole> lstUserRole=new ArrayList<>();						
		this.name = name;
		this.email = email;
		for(String s:roles) {
			UserRole ur=new UserRole(s, this);
			lstUserRole.add(ur);
		}
		this.setRoles(lstUserRole);
		
	}
//    @Override
//    public String toString() {
//    	String strRoles="";
//    	for(UserRole r:getRoles()) {
//    		strRoles = strRoles + r.toString() + "," ;
//    	}
//    	strRoles=strRoles.substring(0, strRoles.length()-1);
//    	
//    	return "userId:" + getUserId() + "," +
//		        "name:"+ getName() + "," +
//		        "email:"+ getEmail() + "," +
//		        "userroles:" + strRoles;
//    }
}
