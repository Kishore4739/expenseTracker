package com.expenseTracker.repository;

import com.expenseTracker.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUser_Id(Long userId);



    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.user.id = :userId")
    Double getTotalExpense(@Param("userId") Long userId);

    List<Expense> findTop5ByUser_EmailOrderByDateDesc(String email);

    Page<Expense> findByUser_Email(String email, Pageable pageable);


    @Query("SELECT SUM(e.amount) FROM Expense e")
    Double getTotalRevenue();

    @Query("""
        SELECT YEAR(e.date), MONTH(e.date), SUM(e.amount)
        FROM Expense e
        WHERE e.date IS NOT NULL
        GROUP BY YEAR(e.date), MONTH(e.date)
        ORDER BY YEAR(e.date), MONTH(e.date)
        """)
    List<Object[]> getMonthlyRevenue();

    @Query("""
        SELECT COALESCE(c.name, 'Unknown'), SUM(e.amount)
        FROM Expense e
        JOIN e.category c
        GROUP BY c.name
        ORDER BY SUM(e.amount) DESC
        """)
    List<Object[]> getTopCategories();

    @Query("""
        SELECT MONTH(e.date), SUM(e.amount)
        FROM Expense e
        WHERE e.user.email = :email
        GROUP BY MONTH(e.date)
        ORDER BY MONTH(e.date)
        """)
    List<Object[]> getUserMonthlySpending(String email);

    @Query("""
        SELECT c.name, SUM(e.amount)
        FROM Expense e
        JOIN e.category c
        WHERE e.user.email = :email
        GROUP BY c.name
        ORDER BY SUM(e.amount) DESC
        """)
    List<Object[]> getUserTopCategories(String email);
}