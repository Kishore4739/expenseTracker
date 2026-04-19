package com.expenseTracker.service;


import com.expenseTracker.model.Category;
import com.expenseTracker.model.User;
import com.expenseTracker.repository.CategoryRepository;
import com.expenseTracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryService(CategoryRepository categoryRepository,
                           UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }
    public Category createCategoryByEmail(String email, Category category) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        category.setUser(user);

        return categoryRepository.save(category);
    }

    public List<Category> getCategoriesByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return categoryRepository.findByUser_Id(user.getId());
    }

}
