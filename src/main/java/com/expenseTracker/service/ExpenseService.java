package com.expenseTracker.service;

import com.expenseTracker.dto.ExpenseRequest;
import com.expenseTracker.dto.ExpenseResponse;
import com.expenseTracker.Exception.ResourceNotFoundException;
import com.expenseTracker.model.Category;
import com.expenseTracker.model.Expense;
import com.expenseTracker.model.User;
import com.expenseTracker.repository.CategoryRepository;
import com.expenseTracker.repository.ExpenseRepository;
import com.expenseTracker.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseService(ExpenseRepository expenseRepository,
                          UserRepository userRepository,
                          CategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

   // get expenses
    public List<ExpenseResponse> getUserExpensesByEmail(String email) {
        log.info("Fetching expenses for user {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return expenseRepository.findByUser_Id(user.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();

    }

    // MAPPER METHOD
    private ExpenseResponse mapToResponse(Expense expense) {
        ExpenseResponse response = new ExpenseResponse();
        response.setId(expense.getId());
        response.setTitle(expense.getDescription());
        response.setAmount(expense.getAmount());
        response.setCategory(expense.getCategory().getName());
        return response;
    }
    //delete expense
    public void deleteExpenseByEmail(String email, Long expenseId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        if (!expense.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        log.info("Deleting expense {} for user {}", expenseId, email);
        expenseRepository.delete(expense);
    }
    // total expenses
    public BigDecimal getTotalExpenseByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Double total = expenseRepository.getTotalExpense(user.getId());

        return total != null ? BigDecimal.valueOf(total) : BigDecimal.ZERO;
    }
    // add expenses
    public ExpenseResponse addExpenseByEmail(String email, ExpenseRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        if (!category.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("you are not allowed authorize the resource");
        }
        log.info("Adding expense for user {}", email);
        Expense expense = new Expense();
        expense.setDescription(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setUser(user);
        expense.setCategory(category);
        expense.setCreatedAt(LocalDateTime.now());

        Expense saved = expenseRepository.save(expense);

        return mapToResponse(saved);
    }

    public List<Map<String, Object>> getUserMonthlySpending(String email) {
        List<Object[]> results = expenseRepository.getUserMonthlySpending(email);

        return results.stream().map(r -> {
            Map<String, Object> map = new HashMap<>();
            map.put("month", r[0]);
            map.put("total", (BigDecimal) r[1]);
            return map;
        }).toList();
    }

    public List<Map<String, Object>> getUserTopCategories(String email) {
        List<Object[]> results = expenseRepository.getUserTopCategories(email);

        return results.stream().map(r -> {
            Map<String, Object> map = new HashMap<>();
            map.put("category", r[0]);
            map.put("total", (BigDecimal) r[1]);
            return map;
        }).toList();
    }

    public List<ExpenseResponse> getRecentExpenses(String email) {
        return expenseRepository
                .findTop5ByUser_EmailOrderByDateDesc(email)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public Page<ExpenseResponse> getExpensesWithPagination(String email, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Expense> expensePage =
                expenseRepository.findByUser_Email(email, pageable);

        return expensePage.map(this::mapToResponse);
    }
}

