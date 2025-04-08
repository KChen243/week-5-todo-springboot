package com.example.kchen.todo.springboot.controller;

import com.example.kchen.todo.springboot.entity.ExceptionResponse;
import com.example.kchen.todo.springboot.exception.TaskNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleExceptions(TaskNotFound e) {
		ExceptionResponse error = new ExceptionResponse();

		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setTimestamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleExceptions(RuntimeException e) {
		ExceptionResponse error = new ExceptionResponse();

		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		error.setTimestamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
