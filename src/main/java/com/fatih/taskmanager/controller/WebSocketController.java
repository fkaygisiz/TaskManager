package com.fatih.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fatih.taskmanager.entity.Task;
import com.fatih.taskmanager.generator.TaskGenerator;
import com.fatih.taskmanager.util.TaskConstants;

@Controller
public class WebSocketController {

	@Autowired
	private TaskGenerator taskGenerator;

	@MessageMapping("/send/message")
	@SendTo(TaskConstants.NEW_TASK_SUBJECT)
	public String sendMessage(@Payload String taskStr) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Task task = taskGenerator.generateAndSaveTask(Math.random());
		return objectMapper.writeValueAsString(task);
	}
}
