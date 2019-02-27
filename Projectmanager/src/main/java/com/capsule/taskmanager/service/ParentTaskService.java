package com.capsule.taskmanager.service;

import java.util.List;

import javax.validation.Valid;

import com.capsule.taskmanager.model.ParentTask;
import com.capsule.taskmanager.model.Task;

public interface ParentTaskService {

	void createParentTask(@Valid ParentTask parentTask);
	List<ParentTask> getParentTask();
	ParentTask findById(Long parenatTaskId);
	ParentTask findByTaskName(String parentTaskName);
	void editParentTask(ParentTask task, Long parenatTaskId);
	void deleteParentTask(Long parenatTaskId);
	
}
