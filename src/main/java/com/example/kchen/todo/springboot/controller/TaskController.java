package com.example.kchen.todo.springboot.controller;

import com.example.kchen.todo.springboot.entity.Task;
import com.example.kchen.todo.springboot.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {
	private TaskService taskService;

	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping("")
	public List<Task> findAll() {
		return this.taskService.findAll();
	}

	@GetMapping("/status")
	@ResponseBody
	public List<Task> findTaskByStatus(@RequestParam(name = "status") String status) {
		return this.taskService.findTaskByStatus(status);
	}

	@GetMapping("/{id}")
	public Task findById(@PathVariable(name = "id") int id) {
		return this.taskService.findById(id);
	}

	@PostMapping("")
	public Task add(@RequestBody Task newTask) {
		return this.taskService.add(newTask);
	}

	@PutMapping("/{id}")
	public Task editTask(@PathVariable(name = "id") int id, @RequestBody Task updatedTask) {
		return this.taskService.edit(id, updatedTask);
	}

	@DeleteMapping("/{id}")
	public Task deleteById(@PathVariable(name = "id") int id) {
		return this.taskService.deleteById(id);
	}

}
