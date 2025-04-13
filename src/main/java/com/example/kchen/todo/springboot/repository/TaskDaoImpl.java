package com.example.kchen.todo.springboot.repository;

import com.example.kchen.todo.springboot.entity.Task;
import com.example.kchen.todo.springboot.exception.TaskNotFoundexception;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TaskDaoImpl implements TaskDao {
	private final EntityManager entityManager;

	public TaskDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	public List<Task> findAll() {
		TypedQuery<Task> query = this.entityManager.createQuery(
				"From Task",
				Task.class
		);

		return query.getResultList();
	}

	@Override
	public Task find(Integer id) {
		Task task = this.entityManager.find(Task.class, id);
		if (task == null) {
			throw new TaskNotFoundexception("Task id: " + id + " is not found!");
		}

		return task;
	}

	@Override
	public Task add(Task newTask) {
		this.entityManager.persist(newTask);
		return newTask;
	}

	@Override
	public Task update(Task updatedTask) {
		this.entityManager.merge(updatedTask);
		return updatedTask;
	}

	@Override
	public Task delete(Integer id) {
		Task task = this.find(id);
		if (task != null) {
			task.setDeleted(true);
			return task;
		}

		return null;
	}
}
