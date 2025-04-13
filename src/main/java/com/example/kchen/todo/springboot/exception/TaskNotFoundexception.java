package com.example.kchen.todo.springboot.exception;

public class TaskNotFoundexception extends RuntimeException {
	public TaskNotFoundexception(String message) {
		super(message);
	}
}
