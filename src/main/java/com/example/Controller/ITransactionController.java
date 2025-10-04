package com.example.Controller;

import com.example.dto.DtoTransaction;
import com.example.dto.DtoTransactionIU;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * ITransactionController
 *
 * Interface for Transaction Controller operations.
 * Defines the contract for CRUD methods related to Transaction entity.
 */
public interface ITransactionController {

    /**
     * Create a new transaction
     * @param dtoTransaction input data transfer object
     * @return created transaction as ResponseEntity
     */
    ResponseEntity<DtoTransaction> createTransaction(DtoTransactionIU dtoTransaction);

    /**
     * Get all transactions
     * @return list of transactions
     */
    ResponseEntity<List<DtoTransaction>> getAllTransactions();

    /**
     * Delete a transaction by ID
     * @param id transaction ID
     * @return status message
     */
    ResponseEntity<String> deleteTransaction(Long id);

    /**
     * Update an existing transaction by ID
     * @param id transaction ID
     * @param dtoTransactionIU input data for update
     * @return updated transaction
     */
    ResponseEntity<DtoTransaction> updateTransaction(Long id, DtoTransactionIU dtoTransactionIU);
}
