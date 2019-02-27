package com.capsule.taskmanager.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capsule.taskmanager.exceptionhandler.ResourceNotFoundException;
import com.capsule.taskmanager.model.ParentTask;
import com.capsule.taskmanager.model.Task;
import com.capsule.taskmanager.repository.ParentTaskRepo;
import com.capsule.taskmanager.repository.TaskRepo;
import com.capsule.taskmanager.service.TaskService;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

	//@Autowired
	private TaskRepo taskRepo;
	private ParentTaskRepo parentTaskRepo;

	@Autowired
	public TaskServiceImpl(TaskRepo taskRepo, ParentTaskRepo parentTaskRepo) {
		this.taskRepo = taskRepo;
		this.parentTaskRepo = parentTaskRepo;
	}

	@Override
	public Task createTask(Task task) {			
		ParentTask parentTask = parentTaskRepo.findTaskByName(task.getParentTaskName());
		if((task.getPriorityTo().equals(null))){
			task.setPriorityTo(15);//if user dose not set priority to task,it will set default value = 15 
		}
		task.setPriorityFrom(0);
		task.setTaskStatus("Started");
		if(parentTask!=null) {
			task.setParentTask(parentTask);//if parent-task found then assign old one			
		}else {
			//create new parent-task in DB if not found 			
			ParentTask newParentTask = new ParentTask();
			newParentTask.setParentTaskName(task.getParentTaskName());
			parentTaskRepo.save(newParentTask);
			task.setParentTask(newParentTask);//assign new parent task to task object			
		}	
		taskRepo.save(task);
		return task;
	}
	@Override
	public List<Task> getTask() {		
		List<Task> taskList = taskRepo.findAll();		
		for(Task task : taskList) {
			task.setParentId(task.getParentTask().getParentTaskId());
			task.setParentTaskName(task.getParentTask().getParentTaskName());			
		}
		return taskList;
	}

	@Override
	public Task findById(Long taskId) {	
		System.out.println("Inside FindById Service");
		Task task = taskRepo.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task", "TaskId", taskId));
		task.setParentTask(parentTaskRepo.getOne(task.getParentTask().getParentTaskId()));
		task.setParentId(task.getParentTask().getParentTaskId());
		return task;
	}

	@Override
	public Task findTaskByName(String taskName) {	
		//System.out.println("Inside findByName Service");
		Task task = taskRepo.findTaskByName(taskName);
		task.setParentTask(parentTaskRepo.getOne(task.getParentTask().getParentTaskId()));
		task.setParentId(task.getParentTask().getParentTaskId());
		return task;
	}

	@Override
	public void editTask(Task updateTask, Long taskId) {
		//System.out.println("Inside Edit task"+updateTask.toString());
		Task task = taskRepo.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task", "TaskId", taskId));
		task.setEndDate(updateTask.getEndDate());
		task.setParentId(updateTask.getParentId());
		task.setStartDate(updateTask.getStartDate());
		task.setTaskId(updateTask.getTaskId());
		task.setTaskName(updateTask.getTaskName());
		task.setPriorityFrom(updateTask.getPriorityFrom());//default priority is set to 0(Zero)
		task.setPriorityTo(updateTask.getPriorityTo());
		task.setParentTask(parentTaskRepo.getOne(updateTask.getParentTask().getParentTaskId()));
		task.setTaskStatus(updateTask.getTaskStatus());//default task status is set to Started
		taskRepo.save(task);
	}
	@Override
	public void finishTask(Long taskId) {		
		Task task = taskRepo.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task", "TaskId", taskId));		
		task.setEndDate(task.getEndDate());
		task.setParentId(task.getParentId());
		task.setStartDate(task.getStartDate());
		task.setTaskId(task.getTaskId());
		task.setTaskName(task.getTaskName());
		task.setPriorityFrom(task.getPriorityFrom());//default priority is set to 0(Zero)
		task.setPriorityTo(task.getPriorityTo());		
		task.setParentTask(parentTaskRepo.getOne(task.getParentTask().getParentTaskId()));
		task.setTaskStatus("Finished");//task status is set to Finished		
		taskRepo.save(task);		
	}
}
