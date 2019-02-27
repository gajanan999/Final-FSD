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

import com.capsule.taskmanager.model.Task;
import com.capsule.taskmanager.service.TaskService;

@RestController
@RequestMapping(value="/taskmanager")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
public class TaskController {	

	@Autowired
	@Qualifier("taskService")
	private TaskService taskService;

	@GetMapping(value="/alltasks")
	public List<Task> listAllTasks(){		
		return taskService.getTask();
	}

	/*@GetMapping(value="/alltasks/{taskId}")
	public Task getTaskById(@PathVariable("taskId") Long taskId) {		
		return taskService.findById(taskId);
	}*/	
	
	@GetMapping(value="/alltasks/{taskName}")
	public Task findTaskByName(@PathVariable("taskName") String taskName) {	
		//System.out.println("findTaskByName :"+taskName);
		return taskService.findTaskByName(taskName);
	}
	
	@PutMapping(value="/alltasks/{taskId}")
	public void editTask(@PathVariable(value = "taskId") Long taskId,@Valid @RequestBody Task taskDetails) {
		taskService.editTask(taskDetails, taskId);
	}

	@PostMapping(value="/alltasks")	
	public ResponseEntity<Void>  createTask(@Valid @RequestBody Task task) {
		taskService.createTask(task);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{taskId}").buildAndExpand(task.getTaskId()).toUri();
		return ResponseEntity.created(location).build();
		
	}

	@DeleteMapping(value="/alltasks/{taskId}")
	public void finishTask(@PathVariable(value = "taskId") Long taskId) {		
		taskService.finishTask(taskId);
	}

}
