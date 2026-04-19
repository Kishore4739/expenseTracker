package com.expenseTracker.controller;

import com.expenseTracker.model.Category;
import com.expenseTracker.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    public Category createCategory(HttpServletRequest request,
                                   @RequestBody Category category) {

        String email = (String) request.getAttribute("userEmail");

        return categoryService.createCategoryByEmail(email, category);
    }


    @GetMapping
    public List<Category> getCategories(HttpServletRequest request) {

        String email = (String) request.getAttribute("userEmail");

        return categoryService.getCategoriesByEmail(email);
    }
}
