package com.example.kchen.todo.springboot.repository;

import com.example.kchen.todo.springboot.entity.Task;

import java.util.List;

public interface TaskDao {
	List<Task> findAll();

	Task findById(Integer id);

	Task add(Task newTask);

	Task edit(Task updatedTask);

	Task deleteById(Integer id);
}
