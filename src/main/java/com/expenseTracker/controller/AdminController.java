package com.expenseTracker.controller;

import com.expenseTracker.dto.AdminStats;
import com.expenseTracker.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/stats")
    public AdminStats getStats() {
        return adminService.getStats();
    }

    @GetMapping("/monthly-revenue")
    public List<Map<String, Object>> getMonthlyRevenue() {
        return adminService.getMonthlyRevenue();
    }

    @GetMapping("/top-categories")
    public List<Map<String, Object>> getTopCategories() {
        return adminService.getTopCategories();
    }

    @GetMapping("/user-stats")
    public Map<String, Long> getUserStats() {
        return adminService.getUserStats();
    }


}
