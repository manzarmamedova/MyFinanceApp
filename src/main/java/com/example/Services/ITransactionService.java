package com.example.Services;

import com.example.dto.DtoTransaction;
import com.example.dto.DtoTransactionIU;
import com.example.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Service interface for managing transactions.
 */
public interface ITransactionService {

    /**
     * Creates a new transaction.
     *
     * @param dtoTransactionIU transaction data from client
     * @return created transaction as DTO
     */
    DtoTransaction createTransaction(DtoTransactionIU dtoTransactionIU);

    /**
     * Retrieves all transactions.
     *
     * @return list of all transactions
     */
    List<DtoTransaction> getAllTransactions();

    /**
     * Deletes a transaction by its ID.
     *
     * @param id transaction ID
     */
    void deleteTransaction(Long id);

    /**
     * Updates an existing transaction.
     *
     * @param id transaction ID
     * @param dtoTransactionIU updated transaction data
     * @return updated transaction as DTO
     */
    DtoTransaction updateTransaction(Long id, DtoTransactionIU dtoTransactionIU);

    /**
     * Retrieves a transaction by its ID.
     *
     * @param id transaction ID
     * @return transaction as DTO
     */
    DtoTransaction getTransactionById(Long id);

    /**
     * Retrieves transactions filtered by user, category, and date range.
     *
     * @param userId user ID
     * @param categoryId category ID (optional)
     * @param fromDate start date
     * @param toDate end date
     * @param pageable pagination information
     * @return paginated transactions
     */
    Page<DtoTransaction> getTransactionsFiltered(
            Long userId,
            Long categoryId,
            LocalDate fromDate,
            LocalDate toDate,
            Pageable pageable
    );

    /**
     * Calculates total amount of transactions for a user in a given date range.
     *
     * @param userId user ID
     * @param fromDate start date
     * @param toDate end date
     * @return total amount as BigDecimal
     */
    BigDecimal getTotalAmountByUserAndDateRange(Long userId, LocalDate fromDate, LocalDate toDate);
}
