package com.capsule.taskmanager.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="parenttask")
public class ParentTask  {
	
  	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name = "parenttaskid")
	private Long parentTaskId;

	@Column(name = "parenttaskname")
	private String parentTaskName;
	
	@OneToMany(fetch = FetchType.EAGER,targetEntity = Task.class ,mappedBy ="parentTask",cascade= {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.DETACH,CascadeType.REMOVE})
	@JsonIgnoreProperties("parentTask")	
	private Set<Task> taskSet = new HashSet<Task>();
	
	public ParentTask() {
		super();
	}	

	public ParentTask(Long parentTaskId, String parentTaskName, Set<Task> taskSet) {
		super();
		this.parentTaskId = parentTaskId;
		this.parentTaskName = parentTaskName;
		this.taskSet = taskSet;
	}

	public Long getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(Long parentTaskId) {
		this.parentTaskId = parentTaskId;
	}	

	public String getParentTaskName() {
		return parentTaskName;
	}

	public void setParentTaskName(String parentTaskName) {
		this.parentTaskName = parentTaskName;
	}

	@Override
	public String toString() {
		return "ParentTask [parentTaskId=" + parentTaskId + ", parentTaskName=" + parentTaskName + "]";
	}	

}
