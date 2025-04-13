package com.example.kchen.todo.springboot.exception;

public class CategoryNotFoundException extends RuntimeException {
	public CategoryNotFoundException(String message) {
		super(message);
	}
}
