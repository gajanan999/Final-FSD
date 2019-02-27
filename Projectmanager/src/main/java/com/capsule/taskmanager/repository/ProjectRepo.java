package com.capsule.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capsule.taskmanager.model.Project;
import com.capsule.taskmanager.model.Users;

public interface ProjectRepo extends JpaRepository<Project,Long>{

	@Query("SELECT p FROM Project p WHERE p.projectName = :projectName")
	Project findProjectByProjectName(@Param("projectName")String projectName);
	

}
