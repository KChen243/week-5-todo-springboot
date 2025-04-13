package com.example.kchen.todo.springboot.controller;

import com.example.kchen.todo.springboot.entity.Category;
import com.example.kchen.todo.springboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(
		origins = "*",
		allowedHeaders = "*",
		methods = {RequestMethod.PUT, RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class CategoryController {
	private CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("")
	public List<Category> findAll() {
		return this.categoryService.findAll();
	}

	@GetMapping("/{id}")
	public Category findById(@PathVariable(name = "id") Integer id) {
		return this.categoryService.findById(id);
	}

	@PostMapping("")
	public Category add(@RequestBody Category newCategory) {
		return this.categoryService.add(newCategory);
	}

	@PutMapping("/{id}")
	public Category editCategory(@PathVariable(name = "id") Integer id, @RequestBody Category updatedCategory) {
		return this.categoryService.edit(id, updatedCategory);
	}

	@DeleteMapping("/{id}")
	public Category deleteById(@PathVariable(name = "id") Integer id) {
		return this.categoryService.deleteById(id);
	}

}
