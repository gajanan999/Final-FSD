package com.capsule.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capsule.taskmanager.model.Task;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long>{
	@Query("SELECT t FROM Task t WHERE t.taskName = :taskName and t.parentTaskName = :parentTaskName")
	Task findTaskByNameAndParent(@Param("taskName")String taskName,@Param("parentTaskName")String parentTaskName);
	
	@Query("SELECT t FROM Task t WHERE t.taskName = :taskName")
	Task findTaskByName(@Param("taskName")String taskName);
}
