package com.fatih.taskmanager.dto;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fatih.taskmanager.entity.Priority;
import com.fatih.taskmanager.entity.Status;

import static com.fatih.taskmanager.util.TaskConstants.DATE_TIME_FORMAT;

public class TaskDTO {

	@JsonProperty("id") 
	private UUID id;

	@JsonFormat(pattern = DATE_TIME_FORMAT)
	@NotNull
	private Date createdAt;

	@JsonFormat(pattern = DATE_TIME_FORMAT)
	@NotNull
	private Date updatedAt;

	@JsonFormat(pattern = DATE_TIME_FORMAT)
	@NotNull
	private Date dueDate;

	@JsonFormat(pattern = DATE_TIME_FORMAT)
	private Date resolvedAt;

	@NotNull
	@Max(200)
	private String title;

	@Column(length = 2000)
	@NotNull
	private String description;

	@NotNull
	private Priority priority;

	@NotNull
	private Status status;
	
	@JsonFormat(pattern = DATE_TIME_FORMAT)
	private Date postponedDate;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getResolvedAt() {
		return resolvedAt;
	}

	public void setResolvedAt(Date resolvedAt) {
		this.resolvedAt = resolvedAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getPostponedDate() {
		return postponedDate;
	}

	public void setPostponedDate(Date postponedDate) {
		this.postponedDate = postponedDate;
	}

}
