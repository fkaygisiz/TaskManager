package com.fatih.taskmanager.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fatih.taskmanager.entity.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, UUID> {

	@Override 
	List<Task> findAll();
}
