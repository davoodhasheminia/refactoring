package com.sap.refactoring.controller;

import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sap.refactoring.entity.User;
import com.sap.refactoring.entity.UserRole;
import com.sap.refactoring.service.UserRoleService;
import com.sap.refactoring.service.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired 	
	UserRoleService userRoleService;
	
	//==============================================================================
    //==============================================================================
	
	@RequestMapping(method = RequestMethod.POST)
	public Long createUser(@RequestBody @Validated UserDto userDto) {
		return userService.createSingleUser(userDto);
	}
	
	@RequestMapping(path = "/{userId}/roles",method = RequestMethod.POST)
	public List<Long> createUserRole(@PathVariable(value = "userId") Long userId,
			                    @RequestBody @Validated UserRoleDto userRoleDto) {
		return userRoleService.addUserRoles(userId,userRoleDto);
	}
	
	//==============================================================================
    //==============================================================================
	
	@RequestMapping(path = "/{userId}", method = RequestMethod.PUT)
	public String updateUser(@PathVariable(value = "userId") Long userId, @RequestBody @Validated UserDto userDto) {
		return userService.updateUser(userId, userDto);
	}
		
    //==============================================================================
    //==============================================================================
	@RequestMapping(method = RequestMethod.GET)
	public List<User> getUsers() {
		return userService.findAllUser();
	}

	@RequestMapping(path = "/{userId}", method = RequestMethod.GET)
	public User getSingleUser(@PathVariable(value = "userId") Long userId) {
		return userService.findSingleUser(userId);
	}
	
	@RequestMapping(path = "/{userId}/roles", method = RequestMethod.GET)
	public User getAllRolesOfUser(@PathVariable(value = "userId") Long userId) {
		return userService.findSingleUser(userId);
	}
	
	@RequestMapping(path = "/{userId}/roles/{roleId}", method = RequestMethod.GET)
	public UserRole getSingleRoleOfUser(@PathVariable(value = "userId") Long userId,
									    @PathVariable(value = "roleId") Long roleId) {
		return userService.findSingleRoleOfUser(userId, roleId);
	}	
	//==============================================================================
	//Delete User
	//==============================================================================	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteAllUsers() {
		userService.deleteAllUsers();
	}
	
	@RequestMapping(path = "/{userId}", method = RequestMethod.DELETE)
	public Long deleteSingleUser(@PathVariable(value = "userId") Long userId) {
		return userService.deleteSingleUser(userId);
	}
	//==============================================================================
    //Delete Role	
	//==============================================================================
	@RequestMapping(path = "/{userId}/roles/{roleId}",method = RequestMethod.DELETE)
	public void deleteSingleUserRole(@PathVariable(value = "userId") Long userId,
		    						  @PathVariable(value = "roleId") Long roleId) {
		userRoleService.deleteSingleUserRole(userId,roleId);
	}
	
	@RequestMapping(path = "/{userId}/roles",method = RequestMethod.DELETE)
	public void deleteAllUserRole(@PathVariable(value = "userId") Long userId) {
		userRoleService.deleteAllUserRole(userId);
	}
	
	
}
