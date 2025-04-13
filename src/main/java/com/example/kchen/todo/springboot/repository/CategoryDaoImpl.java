package com.example.kchen.todo.springboot.repository;

import com.example.kchen.todo.springboot.entity.Category;
import com.example.kchen.todo.springboot.exception.CategoryNotFound;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {
	private final EntityManager entityManager;

	public CategoryDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Category> findAll() {
		TypedQuery<Category> query = entityManager.createQuery(
				"FROM Category",
				Category.class
		);
		return query.getResultList();
	}

	@Override
	public Category findById(Integer id) {
		Category category = this.entityManager.find(Category.class, id);
		if (category == null) {
			throw new CategoryNotFound("Category with ID: " + id + " is not found!");
		}

		return category;
	}

	@Override
	public Category add(Category newCategory) {
		this.entityManager.persist(newCategory);
		return newCategory;
	}

	@Override
	public Category update(Category updatedCategory) {
		this.entityManager.merge(updatedCategory);
		return updatedCategory;
	}

	@Override
	public Category delete(Category category) {
		this.entityManager.remove(category);
		return category;
	}
}
