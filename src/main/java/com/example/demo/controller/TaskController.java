package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.persistence.entity.Task;
import com.example.demo.persistence.entity.TaskStatus;
import com.example.demo.service.TaskService;
import com.example.demo.service.dto.TaskInDTO;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	
	@PostMapping
	public Task createTask(@RequestBody TaskInDTO taskInDTO) {
				
	return this.taskService.createTask(taskInDTO);
	}
	
	@GetMapping
	public  List<Task> getAllTask() {
		
		return this.taskService.getAllTask();
		
	}
	
	@GetMapping("/status/{status}")
	public  List<Task> getAllTaskByStatus(@PathVariable("status") TaskStatus status) {
		
		return this.taskService.getTaskByStatus(status);
		
	}
	
	@PatchMapping("/mark_as__finised/{id}")
	public  ResponseEntity<Void> setFinishedTrue(@PathVariable("id")  Long id) {
		
	 this.taskService.setFinishedTrue(id);
	return ResponseEntity.noContent().build();	
	}
	
	
	@DeleteMapping("/mark_as__finised/{id}")
	public  ResponseEntity<Void> removeTask(@PathVariable("id")  Long id) {
		
	 this.taskService.removeTask(id);
	return ResponseEntity.noContent().build();	
	}
}
