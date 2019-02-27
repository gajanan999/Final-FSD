package com.capsule.taskmanager.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capsule.taskmanager.exceptionhandler.ResourceNotFoundException;
import com.capsule.taskmanager.model.ParentTask;
import com.capsule.taskmanager.repository.ParentTaskRepo;
import com.capsule.taskmanager.service.ParentTaskService;


@Service("parentTaskService")
public class ParentTaskServiceImpl implements ParentTaskService{

	//@Autowired
	private ParentTaskRepo parentTaskRepo;
	
	@Autowired
	public ParentTaskServiceImpl(ParentTaskRepo parentTaskRepo) {		
		this.parentTaskRepo = parentTaskRepo;
	}
	
	@Override
	public void createParentTask(ParentTask parentTask) {
		parentTaskRepo.save(parentTask);		
	}
	@Override
	public List<ParentTask> getParentTask() {
		return parentTaskRepo.findAll();
	}
	@Override
	public ParentTask findById(Long parentTaskId) {
		ParentTask parentTask = parentTaskRepo.findById(parentTaskId).orElseThrow(() -> new ResourceNotFoundException("ParentTask", "ParentTaskId", parentTaskId));
		return parentTask;
	}
	@Override
	public void editParentTask(ParentTask task, Long parentTaskId) {
		
		ParentTask parentTask = parentTaskRepo.findById(parentTaskId).orElseThrow(() -> new ResourceNotFoundException("ParentTask", "ParentTaskId", parentTaskId));
		parentTask.setParentTaskId(parentTask.getParentTaskId());
		parentTask.setParentTaskName(parentTask.getParentTaskName());			
		parentTaskRepo.save(parentTask);
	}
	@Override
	public void deleteParentTask(Long parentTaskId) {		
		ParentTask parentTask = parentTaskRepo.findById(parentTaskId).orElseThrow(() -> new ResourceNotFoundException("ParentTask", "ParentTaskId", parentTaskId));
		parentTaskRepo.delete(parentTask);
	}

	@Override
	public ParentTask findByTaskName(String parentTaskName) {
		ParentTask parentTask = parentTaskRepo.findTaskByName(parentTaskName);
		return parentTask;
	}
	
}
