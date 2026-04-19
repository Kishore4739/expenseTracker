package com.expenseTracker.controller;

import com.expenseTracker.dto.ExpenseRequest;
import com.expenseTracker.dto.ExpenseResponse;
import com.expenseTracker.service.ExpenseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // CREATE EXPENSE
    @PostMapping
    public ResponseEntity<ExpenseResponse> addExpense(
            @Valid @RequestBody ExpenseRequest request,
            HttpServletRequest httpRequest
    ) {

        String email = (String) httpRequest.getAttribute("userEmail");

        ExpenseResponse response = expenseService.addExpenseByEmail(email, request);

        return ResponseEntity.ok(response);
    }

    //GET ALL EXPENSES
    @GetMapping
    public List<ExpenseResponse> getExpenses(HttpServletRequest request) {

        String email = (String) request.getAttribute("userEmail");

        return expenseService.getUserExpensesByEmail(email);
    }

    // DELETE EXPENSE
    @DeleteMapping("/{expenseId}")
    public String deleteExpense(HttpServletRequest request,
                                @PathVariable Long expenseId) {

        String email = (String) request.getAttribute("userEmail");

        expenseService.deleteExpenseByEmail(email, expenseId);

        return "Expense deleted successfully";
    }

    //GET TOTAL
    @GetMapping("/total")
    public BigDecimal getTotal(HttpServletRequest request) {

        String email = (String) request.getAttribute("userEmail");

        return expenseService.getTotalExpenseByEmail(email);
    }

    @GetMapping("/monthly")
    public List<Map<String, Object>> getMonthly(HttpServletRequest request) {
        String email = (String) request.getAttribute("userEmail");
        return expenseService.getUserMonthlySpending(email);
    }

    @GetMapping("/top-categories")
    public List<Map<String, Object>> getTopCategories(HttpServletRequest request) {
        String email = (String) request.getAttribute("userEmail");
        return expenseService.getUserTopCategories(email);
    }

    @GetMapping("/recent")
    public List<ExpenseResponse> getRecent(HttpServletRequest request) {
        String email = (String) request.getAttribute("userEmail");
        return expenseService.getRecentExpenses(email);
    }

    @GetMapping("/paginated")
    public Page<ExpenseResponse> getPaginatedExpenses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            HttpServletRequest request) {

        String email = (String) request.getAttribute("userEmail");

        return expenseService.getExpensesWithPagination(email, page, size);
    }
}

