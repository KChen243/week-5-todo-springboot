package com.example.kchen.todo.springboot.repository;

import com.example.kchen.todo.springboot.entity.Task;

import java.util.List;

public interface TaskDao {
	public List<Task> findAll();

	public Task findById(int id);

	public Task add(Task newTask);

	public Task edit(Task updatedTask);

	public Task deleteById(int id);
}
