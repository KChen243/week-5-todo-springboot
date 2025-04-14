package com.example.kchen.todo.springboot.service;

import com.example.kchen.todo.springboot.entity.Category;
import com.example.kchen.todo.springboot.exception.CategoryNotFoundException;
import com.example.kchen.todo.springboot.repository.CategoryDao;
import com.example.kchen.todo.springboot.repository.TaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
	private final CategoryDao categoryDao;

	@Autowired
	public CategoryService(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public List<Category> findAll() {
		return this.categoryDao.findAll();
	}

	public Category find(Integer id) {
		return this.categoryDao.find(id);
	}

	@Transactional
	public Category add(Category newCategory) {
		return this.categoryDao.add(newCategory);
	}

	@Transactional
	public Category update(Integer id, Category updatedCategory) {
		Category tempCategory = this.categoryDao.find(id);
		if (tempCategory == null) {
			throw new CategoryNotFoundException("Category id: " + id + " is not found!");
		}

		tempCategory.setName(updatedCategory.getName());
		return this.categoryDao.update(tempCategory);
	}

	@Transactional
	public Category delete(Integer id) {
		Category category = this.categoryDao.find(id);
		if (category == null) {
			throw new CategoryNotFoundException("Category id: " + id + " is not found!");
		}

		return this.categoryDao.delete(category);
	}

}
