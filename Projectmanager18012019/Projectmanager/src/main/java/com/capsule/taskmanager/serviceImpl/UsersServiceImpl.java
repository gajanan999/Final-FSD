package com.capsule.taskmanager.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capsule.taskmanager.exceptionhandler.ResourceNotFoundException;
import com.capsule.taskmanager.model.Project;
import com.capsule.taskmanager.model.Users;
import com.capsule.taskmanager.repository.ProjectRepo;
import com.capsule.taskmanager.repository.UsersRepo;
import com.capsule.taskmanager.service.UsersService;

@Service("usersService")
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersRepo usersRepo;
	@Autowired
	private ProjectRepo projectRepo;


	@Override
	public void createUsers(Users users) {
		usersRepo.save(users);
	}

	@Override
	public List<Users> getUsers() {
		return usersRepo.findAll();
	}

	@Override
	public Users findUserByFirstName(String firstName) {
	//	try {
			System.out.println("User name is "+firstName);
			Users user =  usersRepo.findUserByFirstName(firstName);
			Set<Project> userSet = user.getProjectSet();
			userSet.forEach(project->{
				project.setUserId(project.getUsers().getUserId());
			});
		//}
		//catch(Exception e) {
		//	e.printStackTrace();
		//	return null;
		//}
		return user;
	}

	@Override
	public Users findUserByLastName(String lastName) {
		Users users = usersRepo.findUserByLastName(lastName);
		return users;
	}

	@Override
	public Users findById(Long userId) {
		Users users = usersRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Users", "UserId", userId));
		return users;
	}

	@Override
	public void editUser(Users users, Long userId) {

	}

	@Override
	public void deleteUser(Long userId) {

	}

}
