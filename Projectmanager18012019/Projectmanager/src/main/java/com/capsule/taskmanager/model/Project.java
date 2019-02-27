package com.capsule.taskmanager.model;

import java.util.Date;

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

@Entity
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "projectid")
	private Long projectId;

	@Column(name = "projectname")
	private String projectName;

	@Column(name = "projectstartdate")
	@JsonFormat(pattern = "dd/mm/yyyy")
	private Date projectStartDate;

	@Column(name = "enddate")
	@JsonFormat(pattern = "dd/mm/yyyy")
	private Date projectEndDate;

	@Column(name= "priorityfrom")
	private Integer priorityFrom;

	@Column(name= "priorityto")
	private Integer priorityTo;	

	@Column(name = "projectstatus")
	private String projectStatus;

	private transient Long userId;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE })
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties("projectSet")
	private Users users;

	public Project() {
		super();
	}

	public Project(Long projectId, String projectName, Date projectStartDate, Date projectEndDate, Integer priorityFrom,
			Integer priorityTo, String projectStatus, Long userId, Users users) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectStartDate = projectStartDate;
		this.projectEndDate = projectEndDate;
		this.priorityFrom = priorityFrom;
		this.priorityTo = priorityTo;
		this.projectStatus = projectStatus;
		this.userId = userId;
		this.users = users;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public Date getProjectEndDate() {
		return projectEndDate;
	}

	public void setProjectEndDate(Date projectEndDate) {
		this.projectEndDate = projectEndDate;
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

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", projectStartDate="
				+ projectStartDate + ", projectEndDate=" + projectEndDate + ", priorityFrom=" + priorityFrom
				+ ", priorityTo=" + priorityTo + ", projectStatus=" + projectStatus + ", users=" + users + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((priorityFrom == null) ? 0 : priorityFrom.hashCode());
		result = prime * result + ((priorityTo == null) ? 0 : priorityTo.hashCode());
		result = prime * result + ((projectEndDate == null) ? 0 : projectEndDate.hashCode());
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
		result = prime * result + ((projectStartDate == null) ? 0 : projectStartDate.hashCode());
		result = prime * result + ((projectStatus == null) ? 0 : projectStatus.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (priorityFrom == null) {
			if (other.priorityFrom != null)
				return false;
		} else if (!priorityFrom.equals(other.priorityFrom))
			return false;
		if (priorityTo == null) {
			if (other.priorityTo != null)
				return false;
		} else if (!priorityTo.equals(other.priorityTo))
			return false;
		if (projectEndDate == null) {
			if (other.projectEndDate != null)
				return false;
		} else if (!projectEndDate.equals(other.projectEndDate))
			return false;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		if (projectStartDate == null) {
			if (other.projectStartDate != null)
				return false;
		} else if (!projectStartDate.equals(other.projectStartDate))
			return false;
		if (projectStatus == null) {
			if (other.projectStatus != null)
				return false;
		} else if (!projectStatus.equals(other.projectStatus))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}

}
