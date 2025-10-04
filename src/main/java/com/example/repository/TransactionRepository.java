package com.example.repository;

import com.example.entity.Category;
import com.example.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * TransactionRepository
 *
 * Repository interface for Transaction entity.
 * Provides CRUD operations and custom query methods for filtering transactions.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /** Delete all transactions belonging to a specific user */
    void deleteAllByUserId(Long userId);

    /** Find all transactions for a specific category */
    List<Transaction> findByCategory(Category category);

    /** Find transactions for a user and category between specific dates with pagination */
    Page<Transaction> findByUserIdAndCategoryIdAndDateBetween(
            Long userId,
            Long categoryId,
            LocalDateTime fromDate,
            LocalDateTime toDate,
            Pageable pageable
    );

    /** Find transactions for a user between specific dates with pagination */
    Page<Transaction> findByUserIdAndDateBetween(
            Long userId,
            LocalDateTime fromDate,
            LocalDateTime toDate,
            Pageable pageable
    );

    /** Find transactions for a user between specific dates without pagination */
    List<Transaction> findByUserIdAndDateBetween(
            Long userId,
            LocalDateTime fromDate,
            LocalDateTime toDate
    );
}
