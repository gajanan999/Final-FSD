package com.capsule.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capsule.taskmanager.model.ParentTask;

@Repository
public interface ParentTaskRepo extends JpaRepository<ParentTask,Long>{
	
	@Query("SELECT p FROM ParentTask p WHERE p.parentTaskName = :parentTaskName")
	ParentTask findTaskByName(@Param("parentTaskName")String parentTaskName);
}
