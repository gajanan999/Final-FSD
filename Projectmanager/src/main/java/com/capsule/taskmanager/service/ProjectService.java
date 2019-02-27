package com.capsule.taskmanager.service;

import java.util.List;

import com.capsule.taskmanager.model.Project;

public interface ProjectService {
	
	void createProject(Project project);
	List<Project> getProject();
	Project findProjectByProjectName(String projectName);
	Project findProjectById(Long projectId);
	void editProject(Project project, Long projectId);
	void suspendProject(Long projectId);

}
