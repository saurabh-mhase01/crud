package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Category;
import com.example.demo.service.CategoryService;



@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	 @Autowired
	    private CategoryService categoryService;

	    @GetMapping
	    public ResponseEntity<Page<Category>> getAllCategories(@RequestParam(defaultValue = "0") int page,
	                                                           @RequestParam(defaultValue = "10") int size) {
	        return new ResponseEntity<>(categoryService.getCategories(page, size), HttpStatus.OK);
	    }

	    @PostMapping
	    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
	        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
	        Optional<Category> category = categoryService.getCategoryById(id);
	        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
	                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
	        return new ResponseEntity<>(categoryService.updateCategory(id, category), HttpStatus.OK);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
	        categoryService.deleteCategory(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
}
