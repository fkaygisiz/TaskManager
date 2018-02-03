package com.fatih.taskmanager.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatih.taskmanager.dao.TaskRepository;
import com.fatih.taskmanager.entity.Task;

@Service
public class TaskManagerService {

	@Autowired
	private TaskRepository taskRepository;

	public Task save(Task task) {
		return taskRepository.save(task);
	}

	public Optional<Task> getTaskById(UUID id) {
		return taskRepository.findById(id);
	}

	public List<Task> getAllTask() {
		return taskRepository.findAll();
	}

}
