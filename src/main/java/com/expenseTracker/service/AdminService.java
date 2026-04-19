package com.expenseTracker.service;

import com.expenseTracker.dto.AdminStats;
import com.expenseTracker.repository.UserRepository;
import com.expenseTracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;

    public AdminService(UserRepository userRepository,
                        ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
    }

    public AdminStats getStats() {

        AdminStats stats = new AdminStats();

        long totalUsers = userRepository.count();
        long activeUsers = userRepository.countByIsActiveTrue();

        Double totalRevenue = expenseRepository.getTotalRevenue();

        stats.setTotalUsers(totalUsers);
        stats.setActiveUsers(activeUsers);
        stats.setTotalRevenue(
                totalRevenue != null ? BigDecimal.valueOf(totalRevenue) : BigDecimal.ZERO
        );

        return stats;
    }
    public List<Map<String, Object>> getMonthlyRevenue() {
        List<Object[]> results = expenseRepository.getMonthlyRevenue();

        return results.stream().map(r -> {
            Map<String, Object> map = new HashMap<>();
            map.put("year", r[0]);
            map.put("month", r[1]);
            map.put("total", r[2]);
            return map;
        }).toList();
    }


    public List<Map<String, Object>> getTopCategories() {
        List<Object[]> results = expenseRepository.getTopCategories();

        return results.stream().map(r -> {
            Map<String, Object> map = new HashMap<>();
            map.put("category", r[0]);

            BigDecimal total = (BigDecimal) r[1];
            map.put("total", total);

            return map;
        }).toList();
    }

    public Map<String, Long> getUserStats() {
        try{
            List<Object[]> data = userRepository.countUsersByRole();

            Map<String, Long> result = new HashMap<>();

            for (Object[] row : data) {
                String role = row[0].toString();
                Long count = (Long) row[1];
                result.put(role, count);
            }

            return result;
        } catch (Exception e) {
            return new HashMap<>();
        }

    }
}