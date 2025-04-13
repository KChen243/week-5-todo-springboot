package com.example.kchen.todo.springboot.controller;

import com.example.kchen.todo.springboot.entity.Task;
import com.example.kchen.todo.springboot.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(
		origins = "*",
		allowedHeaders = "*",
		methods = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class TaskController {
	private final TaskService taskService;

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
	public Task findById(@PathVariable(name = "id") Integer id) {
		return this.taskService.findById(id);
	}

	@PostMapping("")
	public Task add(@RequestBody Task newTask) {
		return this.taskService.add(newTask);
	}

	@PutMapping("/{id}")
	public Task updateTask(@PathVariable(name = "id") Integer id, @RequestBody Task updatedTask) {
		return this.taskService.update(id, updatedTask);
	}

	@DeleteMapping("/{id}")
	public Task deleteById(@PathVariable(name = "id") Integer id) {
		return this.taskService.deleteById(id);
	}

}
