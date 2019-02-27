package com.capsule.taskmanager.controller;

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

import com.capsule.taskmanager.model.ParentTask;
import com.capsule.taskmanager.service.ParentTaskService;


@RestController
@RequestMapping(value="/parenttaskmanager")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
public class ParentTaskController {
	
	@Autowired
	@Qualifier("parentTaskService")
	private ParentTaskService parentTaskService;
	
	@GetMapping(value="/allparenttasks")
	public List<ParentTask> listAllParentTasks(){		
		return parentTaskService.getParentTask();
	}
	@GetMapping(value="/allparenttasks/{parentTaskId}")
	public ParentTask getParentTaskById(@PathVariable("parentTaskId") Long parentTaskId) {
		return parentTaskService.findById(parentTaskId);
	}	
	@PutMapping(value="/allparenttasks/{parentTaskId}")
	public void editParentTask(@PathVariable(value = "parentTaskId") Long parentTaskId,@Valid @RequestBody ParentTask parentTaskDetails) {
		parentTaskService.editParentTask(parentTaskDetails, parentTaskId);
	}		
	@PostMapping(value="/allparenttasks")
	public void createParentTask(@Valid @RequestBody ParentTask parentTask) {
		parentTaskService.createParentTask(parentTask);
	}
	@DeleteMapping(value="/allparenttasks/{parentTaskId}")
	public ResponseEntity<Void> deleteTask(@PathVariable(value = "parentTaskId") Long parentTaskId) {
		parentTaskService.deleteParentTask(parentTaskId);
		return ResponseEntity.ok().build();
	}

}
