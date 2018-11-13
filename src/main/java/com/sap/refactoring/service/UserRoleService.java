package com.sap.refactoring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.refactoring.controller.UserDto;
import com.sap.refactoring.controller.UserRoleDto;
import com.sap.refactoring.entity.User;
import com.sap.refactoring.entity.UserRole;
import com.sap.refactoring.repository.RoleRepository;
import com.sap.refactoring.repository.UserRepository;

@Service 
public class UserRoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public List<Long> addUserRoles(Long userId,UserRoleDto userRoleDto) {
		
		List<UserRole> lstUserRole=new ArrayList<UserRole>();		
		User tmpUser=userRepository.findById(userId).get();
		if(null==tmpUser) {
			throw new RuntimeException("Invalid User");
		}
		
		for(UserRole s:userRoleDto.getLstRoles()) {
			s.setUser(tmpUser);
			lstUserRole.add(s);
		}
		
		Iterable<UserRole> tmpLst=roleRepository.saveAll(lstUserRole);
		
		List<Long> ids=new ArrayList<>();
		for(UserRole u:tmpLst) {
			ids.add(u.getRoleId());
		}
		
//		return (List<UserRole>) roleRepository.saveAll(lstUserRole);
		return ids;
	}
	
	public void deleteSingleUserRole(Long userId,Long roleId) {
		UserRole userRole=roleRepository.findByUserUserIdAndRoleId(userId,roleId);
		roleRepository.delete(userRole);
	}
	
	public void deleteAllUserRole(Long userId) {
		
		List<UserRole> tmpUserRole = roleRepository.findByUserUserId(userId);
		roleRepository.deleteAll(tmpUserRole);
		
		
	}
	
}
