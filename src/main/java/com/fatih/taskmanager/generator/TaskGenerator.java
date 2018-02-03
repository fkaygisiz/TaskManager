package com.fatih.taskmanager.generator;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatih.taskmanager.dao.TaskRepository;
import com.fatih.taskmanager.dto.TaskDTO;
import com.fatih.taskmanager.entity.Priority;
import com.fatih.taskmanager.entity.Status;
import com.fatih.taskmanager.entity.Task;
import com.fatih.taskmanager.util.TaskConstants;

@Component
public class TaskGenerator {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SimpMessagingTemplate template;

	@Scheduled(fixedRate = 1000)
	public void createRandomTask() throws JsonProcessingException {
		double random = Math.random() * 10;
		if (random > 9) {
			ObjectMapper objectMapper = new ObjectMapper();
			Task task = generateAndSaveTask(random);
			String taskStr2 = objectMapper.writeValueAsString(convertToDto(task));
			template.convertAndSend(TaskConstants.NEW_TASK_SUBJECT, taskStr2);
		}
	}

	public Task generateAndSaveTask(double random) {
		Task task = new Task();
		task.setCreatedAt(new Date());
		task.setDescription("description " + random);
		Date date = new Date();
		date.setTime(date.getTime() / 86400000 * 86400000 + 86400000 * Double.valueOf(Math.random() * 15 ).intValue());// due to in 15 days
		task.setDueDate(date);
		task.setPriority(generateRandomEnum(Priority.values()));
		task.setStatus(generateRandomEnum(Status.values()));
		if (Status.COMPLETED != task.getStatus()) {
			task.setResolvedAt(new Date());
		}
		task.setTitle("title" + random);
		task.setUpdatedAt(new Date());
		taskRepository.save(task);
		return task;
	}

	public <E extends Enum<E>> E generateRandomEnum(E[] values) {
		return values[(int) (Math.random() * values.length)];
	}

	private TaskDTO convertToDto(Task task) {
		return modelMapper.map(task, TaskDTO.class);
	}
}
