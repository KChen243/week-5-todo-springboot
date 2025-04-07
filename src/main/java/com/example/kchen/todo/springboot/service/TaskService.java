package com.example.kchen.todo.springboot.service;

import com.example.kchen.todo.springboot.entity.Task;
import com.example.kchen.todo.springboot.repository.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {
	private TaskDao taskDao;

	@Autowired
	public TaskService(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	public List<Task> findAll() {
		return this.taskDao.findAll();
	}

	public Task findById(int id) {
		return this.taskDao.findById(id);
	}

	@Transactional
	public Task add(Task newTask) {
		return this.taskDao.add(newTask);
	}

	@Transactional
	public Task edit(int id, Task udpatedTask) {
		Task tempTask = this.taskDao.findById(id);
		if (tempTask != null) {
			tempTask.setTitle(udpatedTask.getTitle().isEmpty() ? tempTask.getTitle() : udpatedTask.getTitle());
			tempTask.setDescription(udpatedTask.getDescription().isEmpty() ? tempTask.getDescription() : udpatedTask.getDescription());
			tempTask.setCategory(udpatedTask.getCategory().isEmpty() ? tempTask.getCategory() : udpatedTask.getCategory());
			tempTask.setDueDate(udpatedTask.getDueDate() != null ? udpatedTask.getDueDate() : tempTask.getDueDate());
			tempTask.setDeleted(udpatedTask.isDeleted() != tempTask.isDeleted() ? udpatedTask.isDeleted() : tempTask.isDeleted());
			tempTask.setCompleted(udpatedTask.isCompleted() != tempTask.isCompleted() ? udpatedTask.isCompleted() : tempTask.isCompleted());
		}

		return this.taskDao.edit(tempTask);
	}

	@Transactional
	public Task deleteById(int id) {
		return this.taskDao.deleteById(id);
	}
}
