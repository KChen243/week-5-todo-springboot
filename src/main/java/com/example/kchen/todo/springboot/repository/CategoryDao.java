package com.example.kchen.todo.springboot.repository;

import com.example.kchen.todo.springboot.entity.Category;

import java.util.List;

public interface CategoryDao {
	List<Category> findAll();

	Category findByid(Integer id);

	Category add(Category newCategory);

	Category update(Category updatedCategory);

	Category delete(Category category);
}
