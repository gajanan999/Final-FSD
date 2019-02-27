package com.capsule.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capsule.taskmanager.model.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users,Long>{
	
	
	Users findUserByFirstName(String firstName);


Users findUserByLastName(String lastName);
	

}
