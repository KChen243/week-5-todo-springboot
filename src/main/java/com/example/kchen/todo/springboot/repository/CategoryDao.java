package com.example.kchen.todo.springboot.repository;

import com.example.kchen.todo.springboot.entity.Category;

import java.util.List;

public interface CategoryDao {
	public List<Category> findAll();

	public Category findById(int id);

	public Category add(Category newCategory);

	public Category update(Category updatedCategory);

	public Category delete(Category category);
}
