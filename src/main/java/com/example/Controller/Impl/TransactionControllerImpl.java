package com.example.Controller.Impl;

import com.example.Controller.ITransactionController;
import com.example.Services.ITransactionService;
import com.example.dto.DtoTransaction;
import com.example.dto.DtoTransactionIU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * TransactionControllerImpl
 *
 * REST controller implementation for managing transactions.
 * Provides endpoints for CRUD operations, filtering, and summary calculations.
 */
@RestController
@RequestMapping("/transactions")
public class TransactionControllerImpl implements ITransactionController {

    @Autowired
    private ITransactionService transactionService;

    /**
     * Create a new transaction
     * @param dtoTransactionIU input data transfer object
     * @return created transaction
     */
    @Override
    @PostMapping("/save")
    public ResponseEntity<DtoTransaction> createTransaction(@RequestBody DtoTransactionIU dtoTransactionIU) {
        return ResponseEntity.ok(transactionService.createTransaction(dtoTransactionIU));
    }

    /**
     * Get all transactions
     * @return list of transactions
     */
    @Override
    @GetMapping("/get")
    public ResponseEntity<List<DtoTransaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    /**
     * Delete a transaction by ID
     * @param id transaction ID
     * @return status message
     */
    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok("Transaction deleted successfully");
    }

    /**
     * Update an existing transaction by ID
     * @param id transaction ID
     * @param dtoTransactionIU input data for update
     * @return updated transaction
     */
    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<DtoTransaction> updateTransaction(
            @PathVariable Long id,
            @RequestBody DtoTransactionIU dtoTransactionIU) {
        return ResponseEntity.ok(transactionService.updateTransaction(id, dtoTransactionIU));
    }

    /**
     * Get a single transaction by ID
     * @param id transaction ID
     * @return transaction details
     */
    @GetMapping("/{id}")
    public ResponseEntity<DtoTransaction> getTransactionById(@PathVariable Long id) {
        DtoTransaction dto = transactionService.getTransactionById(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * Get transactions filtered by user, category, and date range with pagination
     * @param userId user ID
     * @param categoryId optional category ID
     * @param fromDate start date
     * @param toDate end date
     * @param pageable pagination information
     * @return paginated list of transactions
     */
    @GetMapping("/filter")
    public ResponseEntity<Page<DtoTransaction>> getTransactionsFiltered(
            @RequestParam Long userId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            Pageable pageable
    ) {
        return ResponseEntity.ok(transactionService.getTransactionsFiltered(userId, categoryId, fromDate, toDate, pageable));
    }

    /**
     * Calculate total amount of transactions by user within a date range
     * @param userId user ID
     * @param fromDate start date
     * @param toDate end date
     * @return total transaction amount
     */
    @GetMapping("/summary")
    public ResponseEntity<BigDecimal> getTotalAmountByUserAndDateRange(
            @RequestParam Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate
    ) {
        return ResponseEntity.ok(transactionService.getTotalAmountByUserAndDateRange(userId, fromDate, toDate));
    }
}
