package com.sap.refactoring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sap.refactoring.controller.UserDto;
import com.sap.refactoring.entity.User;
import com.sap.refactoring.entity.UserRole;
import com.sap.refactoring.repository.RoleRepository;
import com.sap.refactoring.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	public Long createSingleUser(UserDto userDto) {
		
		User tmpUser=null;
		
		if(userDto.getLstRoles()!=null) {
			tmpUser=new User(userDto.getName(), 
					              userDto.getEmail(), 
					              userDto.getLstRoles());
		}else {
			tmpUser=new User(userDto.getName(), 
		                     userDto.getEmail());
		}
		
		for(UserRole r: userDto.getLstRoles()) {
			r.setUser(tmpUser);			
		}
		
		User tmpUser1= userRepository.save(tmpUser);
		roleRepository.saveAll(userDto.getLstRoles());
		
		return tmpUser1.getUserId();
				
	}
		
	public Long  deleteSingleUser(Long userId){
		if(userRepository.findById(userId).isPresent()) {
			userRepository.deleteById(userId);
			return userId;
		}
		return new Long(-1);
	}
	
	public void  deleteAllUsers(){
		userRepository.deleteAll();
	}
	
	public User findSingleUser(Long userId){
		
		User tmpUser=userRepository.findById(userId).get();
		if(null!=tmpUser) {
			return(tmpUser);
		}
		return null;
	}
	
	public List<User> findAllUser(){
		
		List<User> userList=new ArrayList<User>();
		Iterable<User> itrUser= userRepository.findAll();
		for(User u:itrUser) {
			userList.add(u);
		}
		return userList;
	}
	
	
	public List<UserRole> findAllRolesOfUser(Long userId){
		
		return roleRepository.findByUserUserId(userId);				
	}
	
	public UserRole findSingleRoleOfUser(Long userId,Long roleId){
		
		
		return roleRepository.findByUserUserIdAndRoleId(userId, roleId);
	}
	
	
	public String updateUser(Long userId,UserDto userDto) {
		
		User tmpUser= userRepository.findById(userId).get() ;		
		List<UserRole> lstUserRole= roleRepository.findByUserUserId(userId);		
		
		if(null!=tmpUser) {
			tmpUser.setEmail(userDto.getEmail());
			tmpUser.setName(userDto.getName());
			tmpUser.setRoles(userDto.getLstRoles());
			
		}else {
			return "Record Not Found";
		}

		for(UserRole r: userDto.getLstRoles()) {
			r.setUser(tmpUser);			
		}
		
		tmpUser.setRoles(userDto.getLstRoles());				
		User tmpUser1= userRepository.save(tmpUser);
		roleRepository.saveAll(userDto.getLstRoles());		
		roleRepository.deleteAll(lstUserRole);		
		return tmpUser1.getUserId().toString();
	}
	
}
