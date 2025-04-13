package com.example.kchen.todo.springboot.service;

import com.example.kchen.todo.springboot.entity.Category;
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

	public Category findById(int id) {
		return this.categoryDao.findById(id);
	}

	@Transactional
	public Category add(Category newCategory) {
		return this.categoryDao.add(newCategory);
	}

	@Transactional
	public Category update(int id, Category updatedCategory) {
		Category tempCategory = this.categoryDao.findById(id);
		if (tempCategory != null) {
			tempCategory.setName(updatedCategory.getName());
		}

		return this.categoryDao.update(tempCategory);
	}

	@Transactional
	public Category deleteById(int id) {
		Category category = this.categoryDao.findById(id);
		if (category == null) {
			return null;
		}

		return this.categoryDao.delete(category);
	}

}
