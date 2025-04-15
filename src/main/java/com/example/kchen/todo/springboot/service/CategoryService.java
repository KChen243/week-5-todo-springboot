package com.example.kchen.todo.springboot.service;

import com.example.kchen.todo.springboot.entity.Category;
import com.example.kchen.todo.springboot.exception.CategoryNotFoundException;
import com.example.kchen.todo.springboot.repository.CategoryDao;
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

	public Category findByid(Integer id) {
		return this.categoryDao.findByid(id);
	}

	@Transactional
	public Category add(Category newCategory) {
		return this.categoryDao.add(newCategory);
	}

	@Transactional
	public Category update(Integer id, Category updatedCategory) {
		Category tempCategory = this.categoryDao.findByid(id);
		if (tempCategory == null) {
			throw new CategoryNotFoundException("Category id: " + id + " is not found!");
		}

		tempCategory.setName(updatedCategory.getName());
		return this.categoryDao.update(tempCategory);
	}

	@Transactional
	public Category delete(Integer id) {
		Category category = this.categoryDao.findByid(id);
		if (category == null) {
			throw new CategoryNotFoundException("Category id: " + id + " is not found!");
		}

		// dereference all parent object before actually deleting it
		category.getTasks().stream()
				.filter(t -> t.getCategory() != null && t.getCategory().getId().equals(id))
				.forEach(t -> t.setCategory(null));


		return this.categoryDao.delete(category);
	}

}
