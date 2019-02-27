package com.capsule.taskmanager.service;

import java.util.List;

import com.capsule.taskmanager.model.Users;

public interface UsersService {
	
	void createUsers(Users users);
	List<Users> getUsers();
	Users findUserByFirstName(String firstName);
	Users findUserByLastName(String lastName);
	Users findById(Long userId);
	void editUser(Users users, Long userId);
	void deleteUser(Long userId);

}
