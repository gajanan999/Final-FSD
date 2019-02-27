package com.capsule.taskmanager.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	private String fieldName;
	private String resourceName;
	private Object fieldValue;
	
	public ResourceNotFoundException(String fieldName, String resourceName, Object fieldValue) {
		super();
		this.fieldName = fieldName;
		this.resourceName = resourceName;
		this.fieldValue = fieldValue;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public Object getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}
	@Override
	public String toString() {
		return "ResourceNotFoundException [fieldName=" + fieldName + ", resourceName=" + resourceName + ", fieldValue="
				+ fieldValue + "]";
	}	
}
