package com.example.kchen.todo.springboot.repository;

import com.example.kchen.todo.springboot.entity.Task;
import com.example.kchen.todo.springboot.exception.TaskNotFound;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TaskDaoImpl implements TaskDao {
	private EntityManager entityManager;

	@Autowired
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
	public Task findById(int id) {
		Task task =  this.entityManager.find(Task.class, id);
		if(task == null) {
			throw new TaskNotFound("Task id: " + id + " is not found!");
		}

		return task;
	}

	@Override
	public Task add(Task newTask) {
		this.entityManager.persist(newTask);
		return newTask;
	}

	@Override
	public Task edit(Task updatedTask) {
		this.entityManager.merge(updatedTask);
		return updatedTask;
	}

	@Override
	public Task deleteById(int id) {
		Task task = this.findById(id);
		if (task != null) {
			this.entityManager.remove(task);
			return task;
		}

		return null;
	}
}
