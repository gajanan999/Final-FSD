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
		
			Users user =  usersRepo.findUserByFirstName(firstName);
			Set<Project> userSet = user.getProjectSet();
			userSet.forEach(project->{
				project.setUserId(project.getUsers().getUserId());
			});
		return user;
	}

	@Override
	public Users findUserByLastName(String lastName) {
		Users user = usersRepo.findUserByLastName(lastName);
		Set<Project> userSet = user.getProjectSet();
		userSet.forEach(project->{
			project.setUserId(project.getUsers().getUserId());
		});
		return user;
	}

	@Override
	public Users findById(Long userId) {
		Users users = usersRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Users", "UserId", userId));
		return users;
	}

	@Override
	public void editUser(Users users, Long userId) {
		
		Users user = usersRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Users", "UserId", userId));
		user.setEmployeeId(users.getEmployeeId());
		user.setFirstName(users.getFirstName());
		user.setLastName(users.getLastName());
		user.setProjectId(users.getProjectId());
		user.setUserId(users.getUserId());
		user.setTaskId(users.getTaskId());
		usersRepo.save(user);		

	}

	@Override
	public void deleteUser(Long userId) {
		
//		Users user = usersRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Users", "UserId", userId));
//		user.setEmployeeId(user.getEmployeeId());
//		user.setFirstName(user.getFirstName());
//		user.setLastName(user.getLastName());
//		user.setProjectId(user.getProjectId());
//		user.setUserId(user.getUserId());
//		user.setTaskId(user.getTaskId());
		usersRepo.deleteById(userId);

	}

}
