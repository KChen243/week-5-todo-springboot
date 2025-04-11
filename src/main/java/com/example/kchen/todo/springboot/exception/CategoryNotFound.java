package com.example.kchen.todo.springboot.exception;

public class CategoryNotFound extends RuntimeException{
	public CategoryNotFound(String message) {
		super(message);
	}
}
