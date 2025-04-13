package com.example.kchen.todo.springboot.exception;

public class TaskNotFound extends RuntimeException {
	public TaskNotFound(String message) {
		super(message);
	}
}
