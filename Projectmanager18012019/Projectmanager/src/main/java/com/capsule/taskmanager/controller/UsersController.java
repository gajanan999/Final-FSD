package com.capsule.taskmanager.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capsule.taskmanager.model.Users;
import com.capsule.taskmanager.service.UsersService;
import com.capsule.taskmanager.serviceImpl.UsersServiceImpl;

@RestController
//@RequestMapping(value="/usersmanager")
@CrossOrigin(origins="*",allowedHeaders="*")
public class UsersController {
	
	@Autowired
	private UsersServiceImpl usersService;
	
	@GetMapping(value="/allusers")//removeqw
	public List<Users> listAllUsers(){		
		return usersService.getUsers();
	}
	
	@GetMapping(value="/allusers/find/{firstName}")
		public Users findUserByFirstName(@PathVariable("firstName") String firstName) {	
		try {
		System.out.println("assdhashdad"+firstName);
		return usersService.findUserByFirstName(firstName);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping(value="/allusers/{lastName}")
		public Users findUserByLastName(@PathVariable("lastName") String lastName) {	
		return usersService.findUserByLastName(lastName);
	}
	
	@PutMapping(value="/allusers/{userId}")
	public void editUser(@PathVariable(value = "userId") Long userId,@Valid @RequestBody Users users) {
		usersService.editUser(users, userId);
	}
	
	@PostMapping(value="/allusers")	//changedremoveq
	public ResponseEntity<Void>  createUsers(@Valid @RequestBody Users users) {
		usersService.createUsers(users);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(users.getUserId()).toUri();
		return ResponseEntity.created(location).build();
		}
	
	@DeleteMapping(value="/allusers/delete/{userId}")//remove q
	public void deleteUser(@PathVariable(value = "userId") Long userId) {		
		usersService.deleteUser(userId);
	}



}
