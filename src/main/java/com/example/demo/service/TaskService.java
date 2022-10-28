package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exceptions.ToDoExceptions;
import com.example.demo.mapper.TaskInDTOToTask;
import com.example.demo.persistence.entity.Task;
import com.example.demo.persistence.entity.TaskStatus;
import com.example.demo.persistence.repository.TaskRepository;
import com.example.demo.service.dto.TaskInDTO;

@Service
public class TaskService {
	
	
	private final TaskRepository repository;
	private final TaskInDTOToTask mapper;
	
	
	public TaskService (TaskRepository repository,TaskInDTOToTask mapper ) {
		this.repository =repository;
		this.mapper = mapper;
	}
	
	public  Task createTask(TaskInDTO taskDTO) {
		
		return this.repository.save(this.mapper.map(taskDTO));
		
	}

	
	public  List<Task> getAllTask() {
		
		return this.repository.findAll();
		
	}
	
	
	public  List<Task> getTaskByStatus(TaskStatus status) {
	return this.repository.findAllByTaskStatus(status);
		
	}
	
	@Transactional
	public  void setFinishedTrue(Long id) {
Optional <Task> optionalTask =	this.repository.findById(id);

if(optionalTask.isEmpty()) {
	throw new ToDoExceptions("Ese id no existe", HttpStatus.NOT_FOUND);
}

	 this.repository.markTaskAsFinished(id);		
	}
	
	
	public  void removeTask(Long id) {
Optional <Task> optionalTask =	this.repository.findById(id);

if(optionalTask.isEmpty()) {
	throw new ToDoExceptions("Ese id no existe", HttpStatus.NOT_FOUND);
}

	 this.repository.deleteById(id);;		
	}
}
