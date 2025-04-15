package com.example.kchen.todo.springboot.repository;

import com.example.kchen.todo.springboot.entity.Category;
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
	public Category findByid(Integer id) {
		return this.entityManager.find(Category.class, id);
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
