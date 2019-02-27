package com.capsule.taskmanager.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capsule.taskmanager.model.Project;
import com.capsule.taskmanager.model.Task;
import com.capsule.taskmanager.service.ProjectService;

@RestController
@RequestMapping(value="/projectmanager")
@CrossOrigin(origins="*",allowedHeaders="*")
public class ProjectController {
	
	@Autowired
	@Qualifier("projectService")
	private ProjectService projectService;
	
	@GetMapping(value="/allprojects")
	public List<Project> listAllProjects(){		
		return projectService.getProject();
	}
	
	/*@GetMapping(value="/allprojects/{projectName}")
	public Project findProjectByProjectName(@PathVariable("projectName") String projectName) {	
		//System.out.println("findTaskByName :"+taskName);
		return projectService.findProjectByProjectName(projectName);
	}*/
	
	
	@GetMapping(value="/allprojects/{projectName}")
	public Project getManagerByfirstName(@PathVariable("projectName") String projectName) {	
		//System.out.println("findTaskByName :"+taskName);
		return projectService.findProjectByProjectName(projectName);
	}
	
	@PutMapping(value="/allprojects/{projectId}")
	public void editProject(@PathVariable(value = "projectId") Long projectId,@Valid @RequestBody Project project) {
		projectService.editProject(project, projectId);
	}
	
	@PostMapping(value="/allprojects")	
	public ResponseEntity<Void>  createProject(@Valid @RequestBody Project project) {
		projectService.createProject(project);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{projectId}").buildAndExpand(project.getProjectId()).toUri();
		return ResponseEntity.created(location).build();
		}
	
	@DeleteMapping(value="/allprojects/{projectId}")
	public void suspendProject(@PathVariable(value = "projectId") Long projectId) {		
		projectService.suspendProject(projectId);
	}


}
