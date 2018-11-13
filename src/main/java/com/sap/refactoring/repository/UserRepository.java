package com.sap.refactoring.repository;


import org.springframework.data.repository.CrudRepository;

import com.sap.refactoring.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	public User findByNameAndEmail(String name,String email);
	
}
