package com.example.kchen.todo.springboot.repository;

import com.example.kchen.todo.springboot.entity.Task;

import java.util.List;

public interface TaskDao {
	List<Task> findAll();

	Task find(Integer id);

	Task add(Task newTask);

	Task update(Task updatedTask);

	Task delete(Integer id);
}
