package com.fatih.taskmanager.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fatih.taskmanager.dto.TaskDTO;
import com.fatih.taskmanager.entity.Status;
import com.fatih.taskmanager.entity.Task;
import com.fatih.taskmanager.service.TaskManagerService;

//@CrossOrigin(origins = { "*" },methods= {RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT, RequestMethod.OPTIONS})
@CrossOrigin
@RestController
@RequestMapping("/tasks")
public class TaskManagerController {

	@Autowired
	private TaskManagerService taskManagerService;

	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody TaskDTO taskDto) throws URISyntaxException {
		Task task = convertFromDto(taskDto);
		Date now = new Date();
		task.setCreatedAt(now);
		task.setUpdatedAt(now);
		task = taskManagerService.save(task);
		return ResponseEntity.created(new URI("/" + task.getId())).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<TaskDTO> update(@PathVariable("id") UUID id, @RequestBody TaskDTO taskDto) {

		Task task = convertFromDto(taskDto);
		Date now = new Date();
		task.setUpdatedAt(now);
		if (task.getStatus() == Status.COMPLETED) {
			task.setResolvedAt(now);
		}
		task = taskManagerService.save(task);
		return ResponseEntity.ok(convertToDto(task));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<TaskDTO> getTask(@PathVariable("id") String id) {
		Optional<Task> task = taskManagerService.getTaskById(UUID.fromString(id));
		if (!task.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(convertToDto(task.get()));
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TaskDTO>> getTasks() {
		List<Task> tasks = taskManagerService.getAllTask();

		return ResponseEntity.ok(convertTasksToTaskDtoList(tasks));
	}

	private List<TaskDTO> convertTasksToTaskDtoList(List<Task> tasks) {
		return tasks.stream().map(e -> modelMapper.map(e, TaskDTO.class)).collect(Collectors.toList());
	}

	private Task convertFromDto(TaskDTO taskDto) {
		return modelMapper.map(taskDto, Task.class);
	}

	private TaskDTO convertToDto(Task task) {
		return modelMapper.map(task, TaskDTO.class);
	}
	
	@RequestMapping(value= "*", method=RequestMethod.OPTIONS)
	public void corsHeaders(HttpServletResponse response) {
	    response.addHeader("Access-Control-Allow-Origin", "*");
	    response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	    response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
	    response.addHeader("Access-Control-Max-Age", "3600");
	}
}
