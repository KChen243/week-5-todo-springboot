package com.example.kchen.todo.springboot.service;

import com.example.kchen.todo.springboot.entity.Task;
import com.example.kchen.todo.springboot.exception.TaskNotFoundException;
import com.example.kchen.todo.springboot.repository.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {
	private final TaskDao taskDao;

	@Autowired
	public TaskService(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	public List<Task> findAll() {
		return this.taskDao.findAll().stream()
				.filter(t->!t.isDeleted())
				.toList();
	}

	public Task find(Integer id) {
		Task task = this.taskDao.find(id);
		if (task == null) {
			throw new TaskNotFoundException("Task id: " + id + " is not found!");
		}
		return task;
	}

	@Transactional
	public Task add(Task newTask) {
		return this.taskDao.add(newTask);
	}

	@Transactional
	public Task update(Integer id, Task udpatedTask) {
		Task tempTask = this.taskDao.find(id);
		if (tempTask == null) {
			throw new TaskNotFoundException("Task id: " + id + " is not found!");
		}

		tempTask.setTitle(udpatedTask.getTitle().isEmpty() ? tempTask.getTitle() : udpatedTask.getTitle());
		tempTask.setDescription(udpatedTask.getDescription().isEmpty() ? tempTask.getDescription() : udpatedTask.getDescription());
		tempTask.setCategory(udpatedTask.getCategory().isEmpty() ? tempTask.getCategory() : udpatedTask.getCategory());
		tempTask.setDueDate(udpatedTask.getDueDate() != null ? udpatedTask.getDueDate() : tempTask.getDueDate());
		tempTask.setDeleted(udpatedTask.isDeleted());
		tempTask.setCompleted(udpatedTask.isCompleted());

		return this.taskDao.update(tempTask);
	}

	@Transactional
	public Task delete(Integer id) {
		return this.taskDao.delete(id);
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
