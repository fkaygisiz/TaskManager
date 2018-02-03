package com.fatih.taskmanager.entity;

import static com.fatih.taskmanager.util.TaskConstants.DATE_TIME_FORMAT;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "Task")
@Table(name = "Task")
public class Task {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	@JsonFormat(pattern = DATE_TIME_FORMAT)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@JsonFormat(pattern = DATE_TIME_FORMAT)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@JsonFormat(pattern = DATE_TIME_FORMAT)
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dueDate;

	@JsonFormat(pattern = DATE_TIME_FORMAT)
	@Temporal(TemporalType.TIMESTAMP)
	private Date resolvedAt;

	@Column(length = 200)
	private String title;

	@Column(length = 2000)
	private String description;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Priority priority;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Status status;

	@JsonFormat(pattern = DATE_TIME_FORMAT)
	@Temporal(TemporalType.TIMESTAMP)
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