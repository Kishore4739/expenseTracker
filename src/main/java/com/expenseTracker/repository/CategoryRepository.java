package com.expenseTracker.repository;

import com.expenseTracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUser_Id(Long userId);
    Optional<Category> findByUserIdAndName(Long userId, String name);

}
