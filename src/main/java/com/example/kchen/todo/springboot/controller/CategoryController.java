package com.example.kchen.todo.springboot.controller;

import com.example.kchen.todo.springboot.entity.Category;
import com.example.kchen.todo.springboot.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("")
	public List<Category> findAll() {
		return this.categoryService.findAll();
	}

	@GetMapping("/{id}")
	public Category find(@PathVariable(name = "id") Integer id) {
		return this.categoryService.find(id);
	}

	@PostMapping("")
	public Category add(@RequestBody Category newCategory) {
		return this.categoryService.add(newCategory);
	}

	@PutMapping("/{id}")
	public Category update(@PathVariable(name = "id") Integer id, @RequestBody Category updatedCategory) {
		return this.categoryService.update(id, updatedCategory);
	}

	@DeleteMapping("/{id}")
	public Category delete(@PathVariable(name = "id") Integer id) {
		return this.categoryService.delete(id);
	}

}
