package com.sap.refactoring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sap.refactoring.entity.User;
import com.sap.refactoring.entity.UserRole;

public interface RoleRepository extends CrudRepository<UserRole, Long>{
	
	public void deleteByUser(User user);
	public void deleteByUserUserId(Long userId);
	public void deleteByRoleIdAndUserUserId(Long userId,Long roleId);
	
	public List<UserRole> findByUserUserId(Long userId);
	public UserRole findByUserUserIdAndRoleId(Long userId,Long roleId);
}
