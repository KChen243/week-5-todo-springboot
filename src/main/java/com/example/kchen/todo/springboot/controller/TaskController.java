package com.example.kchen.todo.springboot.controller;

import com.example.kchen.todo.springboot.dto.TaskCategoryId;
import com.example.kchen.todo.springboot.entity.Task;
import com.example.kchen.todo.springboot.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
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
	public Task find(@PathVariable(name = "id") Integer id) {
		return this.taskService.findById(id);
	}

	@PostMapping("")
	public Task add(@RequestBody TaskCategoryId newTaskDto) {
		return this.taskService.add(newTaskDto);
	}

	@PutMapping("/{id}")
	public Task update(@PathVariable(name = "id") Integer id, @RequestBody TaskCategoryId updatedTask) {
		return this.taskService.update(id, updatedTask);
	}

	@DeleteMapping("/{id}")
	public Task delete(@PathVariable(name = "id") Integer id) {
		return this.taskService.delete(id);
	}

}
