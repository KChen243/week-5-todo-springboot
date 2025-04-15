package com.example.kchen.todo.springboot.exception;

public class TaskNotFoundException extends RuntimeException {
	public TaskNotFoundException(String message) {
		super(message);
	}
}
