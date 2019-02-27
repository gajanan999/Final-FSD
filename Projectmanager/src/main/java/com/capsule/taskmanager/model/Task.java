package com.capsule.taskmanager.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="task")
public class Task {

	

	@Id	
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name = "taskid")	
	private Long taskId;

	@Column(name = "taskname")
	private String taskName;

	@Column(name = "startdate")
	@JsonFormat(pattern="dd/mm/yyyy") 
	private Date startDate;	

	@Column(name = "enddate")
	@JsonFormat(pattern="dd/mm/yyyy") 
	private Date endDate;

	//@Column(name= "priority")
	//private Integer priority;

	@Column(name= "priorityfrom")
	private Integer priorityFrom;

	@Column(name= "priorityto")
	private Integer priorityTo;	

	@Column(name= "parenttaskname")
	private String parentTaskName;

	@Column(name= "taskstatus")
	private String taskStatus;

	private transient Long parentId;

	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.DETACH,CascadeType.REMOVE})
	@JoinColumn(name="parentid")
	@JsonIgnoreProperties("taskSet")
	private ParentTask parentTask;

	public Task() {
		super();
	}

	public Task(Long taskId, String taskName, Date startDate, Date endDate, Integer priorityFrom, Integer priorityTo,
			String parentTaskName, String taskStatus, Long parentId, ParentTask parentTask) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priorityFrom = priorityFrom;
		this.priorityTo = priorityTo;
		this.parentTaskName = parentTaskName;
		this.taskStatus = taskStatus;
		this.parentId = parentId;
		this.parentTask = parentTask;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ParentTask getParentTask() {
		return parentTask;
	}

	public void setParentTask(ParentTask parentTask) {
		this.parentTask = parentTask;
	}	

	public Integer getPriorityFrom() {
		return priorityFrom;
	}

	public void setPriorityFrom(Integer priorityFrom) {
		this.priorityFrom = priorityFrom;
	}

	public Integer getPriorityTo() {
		return priorityTo;
	}

	public void setPriorityTo(Integer priorityTo) {
		this.priorityTo = priorityTo;
	}

	public String getParentTaskName() {
		return parentTaskName;
	}

	public void setParentTaskName(String parentTaskName) {
		this.parentTaskName = parentTaskName;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", priorityFrom=" + priorityFrom + ", priorityTo=" + priorityTo + ", parentTaskName=" + parentTaskName
				+ ", taskStatus=" + taskStatus + ", parentTask=" + parentTask + "]";
	}	

}
