package com.capsule.taskmanager.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capsule.taskmanager.exceptionhandler.ResourceNotFoundException;
import com.capsule.taskmanager.model.Project;
import com.capsule.taskmanager.repository.ProjectRepo;
import com.capsule.taskmanager.repository.UsersRepo;
import com.capsule.taskmanager.service.ProjectService;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService{
	
	private ProjectRepo projectRepo;
	private UsersRepo usersRepo;
	
	@Autowired
	public ProjectServiceImpl(ProjectRepo projectRepo,UsersRepo usersRepo) {		
		this.projectRepo = projectRepo;
		this.usersRepo = usersRepo;
	}

	@Override
	public void createProject(Project saveProject) {
		saveProject.setUsers(usersRepo.getOne(saveProject.getProjectId()));
		saveProject.setUserId(saveProject.getUsers().getUserId());
	
		if((saveProject.getPriorityTo().equals(null))){
			saveProject.setPriorityTo(15);//if user dose not set priority to task,it will set default value = 15 
		}
		saveProject.setPriorityFrom(0);
		saveProject.setProjectStatus("Started");
		
		projectRepo.save(saveProject);
	}

	@Override
	public List<Project> getProject() {
		List<Project> projectList = projectRepo.findAll();
		for(Project project : projectList) {
			project.setUserId(project.getUsers().getUserId());
		}
		return projectList;
	}

	@Override
	public Project findProjectByProjectName(String projectName) {
		Project project = projectRepo.findProjectByProjectName(projectName);
		return project;
	}

	@Override
	public Project findProjectById(Long projectId) {
		Project project = projectRepo.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project", "ProjectId", projectId));
		return project;
	}

	@Override
	public void editProject(Project project, Long projectId) {
		System.out.println("Inside editProject");
		Project newProject = projectRepo.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project", "ProjectId", projectId));
		newProject.setProjectName(project.getProjectName());
		//newProject.setPriority(project.getPriority());
		newProject.setPriorityFrom(project.getPriorityFrom());//default priority is set to 0(Zero)
		newProject.setPriorityTo(project.getPriorityTo());
		newProject.setProjectEndDate(project.getProjectEndDate());
		newProject.setProjectStartDate(project.getProjectStartDate());
		projectRepo.save(newProject);	
		System.out.println(newProject);
	}

	@Override
	public void suspendProject(Long projectId) {
		
		Project newProject = projectRepo.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project", "ProjectId", projectId));
		//newProject.setPriority(newProject.getPriority());
		newProject.setPriorityFrom(newProject.getPriorityFrom());//default priority is set to 0(Zero)
		newProject.setPriorityTo(newProject.getPriorityTo());		
		newProject.setProjectEndDate(newProject.getProjectEndDate());
		newProject.setProjectId(newProject.getProjectId());
		newProject.setProjectName(newProject.getProjectName());
		newProject.setProjectStartDate(newProject.getProjectStartDate());
		newProject.setProjectStatus("Finished");
		projectRepo.save(newProject);
	}

}
