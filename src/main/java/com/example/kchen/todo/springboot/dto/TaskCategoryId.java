package com.example.kchen.todo.springboot.dto;

import com.example.kchen.todo.springboot.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TaskCategoryId {
	private Task task;
	private Integer categoryId;
}
