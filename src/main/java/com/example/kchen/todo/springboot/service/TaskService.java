package com.example.kchen.todo.springboot.service;

import com.example.kchen.todo.springboot.dto.TaskCategoryId;
import com.example.kchen.todo.springboot.entity.Category;
import com.example.kchen.todo.springboot.entity.Task;
import com.example.kchen.todo.springboot.exception.TaskNotFoundException;
import com.example.kchen.todo.springboot.repository.TaskDao;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {
	private final TaskDao taskDao;
	private final CategoryService categoryService;

	public TaskService(TaskDao taskDao, CategoryService categoryService) {
		this.taskDao = taskDao;
		this.categoryService = categoryService;
	}

	public List<Task> findAll() {
		return this.taskDao.findAll().stream()
				.filter(t -> !t.isDeleted())
				.toList();
	}

	public Task findById(Integer id) {
		Task task = this.taskDao.findByid(id);
		if (task == null) {
			throw new TaskNotFoundException("Task id: " + id + " is not found!");
		}
		return task;
	}

	@Transactional
	public Task add(TaskCategoryId newTaskDto) {
		Task newTask = new Task();

		newTask.setTitle(newTaskDto.getTask().getTitle());
		newTask.setDescription(newTaskDto.getTask().getDescription());
		newTask.setDueDate(newTaskDto.getTask().getDueDate());

		if (newTaskDto.getCategoryId() != null) {
			Category category = this.categoryService.findByid(newTaskDto.getCategoryId());
			if (category != null) {
				newTask.setCategory(category);
			}
		}

		return this.taskDao.add(newTask);
	}

	// helper method to determine what string to save
	private String checkUpdate(String updatedValue) {
		if(StringUtils.isEmpty(updatedValue)) {
			return "";
		}

		return updatedValue;
	}

	@Transactional
	public Task update(Integer id, TaskCategoryId updatedTask) {
		if (this.taskDao.findByid(id) == null) {
			throw new TaskNotFoundException("Task id: " + id + " is not found!");
		}

		Task tempTask = new Task();
		tempTask.setId(id);

		tempTask.setTitle(this.checkUpdate(updatedTask.getTask().getTitle()));
		tempTask.setDescription(this.checkUpdate(updatedTask.getTask().getDescription()));
		tempTask.setDueDate(updatedTask.getTask().getDueDate());
		tempTask.setCompleted(updatedTask.getTask().isCompleted());
		if (updatedTask.getCategoryId() != null) {
			Category category = this.categoryService.findByid(updatedTask.getCategoryId());
			if (category != null) {
				tempTask.setCategory(category);
			}

		}

		return this.taskDao.update(tempTask);
	}

	@Transactional
	public Task delete(Integer id) {
		Task task = this.taskDao.findByid(id);
		if (task == null) {
			throw new TaskNotFoundException("Task id: " + id + " is not found!");
		}

		return this.taskDao.delete(task);
	}

	public List<Task> findTaskByStatus(String status) {
		List<Task> tasks = this.taskDao.findAll();

		switch (status) {
			case "completed" -> {
				return tasks
						.stream()
						.filter(t -> t.isCompleted() && !t.isDeleted())
						.toList();
			}
			case "deleted" -> {
				return tasks
						.stream()
						.filter(Task::isDeleted)
						.toList();
			}
			case "overdue" -> {
				Date today = new Date();
				return tasks
						.stream()
						.filter(t -> t.getDueDate().compareTo(today) < 0 && !t.isCompleted() && !t.isDeleted())
						.toList();
			}
			case "upcoming" -> {
				Date today = new Date();
				return tasks
						.stream()
						.filter(t -> !t.isCompleted() && !t.isDeleted() && (t.getDueDate().compareTo(today) > 0 || t.getDueDate().equals(today)))
						.toList();
			}
		}

		throw new RuntimeException("Invalid parameter");
	}
}
