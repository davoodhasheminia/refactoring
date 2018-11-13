package com.sap.refactoring;



import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.sap.refactoring.controller.UserDto;
import com.sap.refactoring.controller.UserRoleDto;
import com.sap.refactoring.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RefactoringApplication.class, 
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RefactoringApplicationTests {
	
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	Long lastUserId;
	Long lastRoleId;
	
	@Before
    public void beforeFunction(){
		headers.add("Accept", "application/json");
		
		UserDto userDto=new UserDto("davood", 
				                    "davoodemail", 
				                    Arrays.asList("a"));
		
		HttpEntity<UserDto> entityUserDto = new HttpEntity<UserDto>(userDto, headers);

		ResponseEntity<String> responseUserDto = restTemplate.exchange(
				createURLWithPort("/users"),
				HttpMethod.POST, entityUserDto, String.class);
		lastUserId=Long.valueOf(responseUserDto.getBody());
		
		
		headers.add("Accept", "application/json");
		
		UserRoleDto userRoleDto=new UserRoleDto(Arrays.asList("Test01","Test02","Test03")); 
		
		HttpEntity<UserRoleDto> entityUserRoleDto = new HttpEntity<UserRoleDto>(userRoleDto, headers);

		ResponseEntity<String> responseUserRoleDto = restTemplate.exchange(
				createURLWithPort("/users/"+ lastUserId + "/roles"),
				HttpMethod.POST, entityUserRoleDto, String.class);
		
		String temp=responseUserRoleDto.getBody().toString();
		lastRoleId= Long.valueOf(temp.substring(temp.length()-2,temp.length()-1));
    }
	
	@Test
	public void testGetAllUsersWithRoles() {
		
		headers.add("Accept", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users"),
				HttpMethod.GET, entity, String.class);
		
		System.out.println("========================================================");
		System.out.println("testGetAllUsersWithRoles--"+response.getBody().toString());
		System.out.println("========================================================");
		
		assertTrue(response.getStatusCode()==HttpStatus.OK |
				   response.getStatusCode()==HttpStatus.ACCEPTED |
				   response.getStatusCode()==HttpStatus.CREATED);
	}
	
	@Test
	public void testGetSingleUsersWithRoles() {
		
		headers.add("Accept", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users/" + lastUserId),
				HttpMethod.GET, entity, String.class);
		System.out.println("========================================================");
		System.out.println("testGetSingleUsersWithRoles--" + response.getBody().toString());
		System.out.println("========================================================");
		assertTrue(response.getStatusCode()==HttpStatus.OK |
				   response.getStatusCode()==HttpStatus.ACCEPTED |
				   response.getStatusCode()==HttpStatus.CREATED);
		
		
	}
	
	
	@Test
	public void testGetAllRolesOfUser() {
		
		headers.add("Accept", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users/" + lastUserId + "/roles"),
				HttpMethod.GET, entity, String.class);
		
		System.out.println("========================================================");
		System.out.println("testGetAllRolesOfUser--" + response.getBody().toString());
		System.out.println("========================================================");
		
		assertTrue(response.getStatusCode()==HttpStatus.OK |
				   response.getStatusCode()==HttpStatus.ACCEPTED |
				   response.getStatusCode()==HttpStatus.CREATED);
	}
	
	
	@Test
	public void testGetSingleRolesOfUser() {
		
		headers.add("Accept", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users/" + lastUserId + "/roles/" + lastRoleId),
				HttpMethod.GET, entity, String.class);
		
		System.out.println("========================================================");
		System.out.println("testGetSingleRolesOfUser--" + response.getBody()!=null?response.getBody():"null");
		System.out.println("========================================================");
		
		assertTrue(response.getStatusCode()==HttpStatus.OK |
				   response.getStatusCode()==HttpStatus.ACCEPTED |
				   response.getStatusCode()==HttpStatus.CREATED);
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	
	@Test
	public void addUser() {

		headers.add("Accept", "application/json");
		
		UserDto userDto=new UserDto("davood", 
				                    "davoodemail", 
				                    Arrays.asList("role01","role02","role03"));
		
		HttpEntity<UserDto> entity = new HttpEntity<UserDto>(userDto, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users"),
				HttpMethod.POST, entity, String.class);
		System.out.println("========================================================");
		System.out.println(response.getBody().toString());
		System.out.println("========================================================");
		
		assertTrue(response.getStatusCode()==HttpStatus.OK |
				   response.getStatusCode()==HttpStatus.ACCEPTED |
				   response.getStatusCode()==HttpStatus.CREATED);
		
	}
	
	@Test
	public void addRoleToUser() {

		headers.add("Accept", "application/json");
		
		UserRoleDto userRoleDto=new UserRoleDto(Arrays.asList("Test01","Test02","Test03")); 
		
		HttpEntity<UserRoleDto> entity = new HttpEntity<UserRoleDto>(userRoleDto, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users/" +  lastUserId+ "/roles"),
				HttpMethod.POST, entity, String.class);
		System.out.println("========================================================");
		System.out.println(response.getBody().toString());
		System.out.println("========================================================");
		assertTrue(response.getStatusCode()==HttpStatus.OK |
				   response.getStatusCode()==HttpStatus.ACCEPTED |
				   response.getStatusCode()==HttpStatus.CREATED);
		
	}
	
	@Test
	public void deleteAllUser() {

		headers.add("Accept", "application/json");
				 
		
		HttpEntity<UserRoleDto> entity = new HttpEntity<UserRoleDto>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users"),
				HttpMethod.DELETE, entity, String.class);
		System.out.println("========================================================");
		System.out.println(response.getBody()!=null?response.getBody():"null");
		System.out.println("========================================================");
		
		assertTrue(response.getStatusCode()==HttpStatus.OK |
				   response.getStatusCode()==HttpStatus.ACCEPTED |
				   response.getStatusCode()==HttpStatus.CREATED);
		
	}
	
	@Test
	public void deleteSingleUser() {

		headers.add("Accept", "application/json");
				 
		
		HttpEntity<UserRoleDto> entity = new HttpEntity<UserRoleDto>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users/" + lastUserId),
				HttpMethod.DELETE, entity, String.class);
		System.out.println("========================================================");
		System.out.println(response.getBody().toString());
		System.out.println("========================================================");
		assertTrue(response.getStatusCode()==HttpStatus.OK |
				   response.getStatusCode()==HttpStatus.ACCEPTED |
				   response.getStatusCode()==HttpStatus.CREATED);
		
	}
	
	@Test
	public void deleteAllRolesOdSingleUser() {

		headers.add("Accept", "application/json");
				 
		
		HttpEntity<UserRoleDto> entity = new HttpEntity<UserRoleDto>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users/" + lastUserId),
				HttpMethod.DELETE, entity, String.class);
		
		System.out.println("========================================================");
		System.out.println(response.getBody().toString());
		System.out.println("========================================================");
		
		assertTrue(response.getStatusCode()==HttpStatus.OK |
				   response.getStatusCode()==HttpStatus.ACCEPTED |
				   response.getStatusCode()==HttpStatus.CREATED);
		
	}
	@Test
	public void deleteSingleRolesOdSingleUser() {

		headers.add("Accept", "application/json");
				 
		
		HttpEntity<UserRoleDto> entity = new HttpEntity<UserRoleDto>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/users/" + lastUserId + "/roles/" + lastRoleId),
				HttpMethod.DELETE, entity, String.class);
		System.out.println("========================================================");
		System.out.println(response.getBody()!=null?response.getBody():"null");
		System.out.println("========================================================");
		assertTrue(response.getStatusCode()==HttpStatus.OK |
				   response.getStatusCode()==HttpStatus.ACCEPTED |
				   response.getStatusCode()==HttpStatus.CREATED);
		
	}
	
}
