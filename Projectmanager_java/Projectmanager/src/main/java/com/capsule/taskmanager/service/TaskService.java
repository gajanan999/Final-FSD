package com.capsule.taskmanager.service;

import java.util.List;

import com.capsule.taskmanager.model.Task;

public interface TaskService {
	
	Task createTask(Task task);
	List<Task> getTask();
	Task findTaskByName(String taskName);
	Task findById(Long taskId);
	void editTask(Task task, Long taskId);
	void finishTask(Long taskId);

}
