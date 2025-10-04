package com.example.Services.Impl;

import com.example.Services.ITransactionService;
import com.example.dto.DtoTransaction;
import com.example.dto.DtoTransactionIU;
import com.example.entity.Category;
import com.example.entity.Transaction;
import com.example.entity.User;
import com.example.exception.CategoryNotFoundException;
import com.example.exception.InvalidRequestException;
import com.example.exception.TransactionNotFoundException;
import com.example.exception.UserNotFoundException;
import com.example.repository.CategoryRepository;
import com.example.repository.TransactionRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Transaction Service Implementation
 * Handles CRUD operations and filtering for transactions
 */
@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Create a new transaction
     */
    @Override
    public DtoTransaction createTransaction(DtoTransactionIU dto) {
        validateAmount(dto.getAmount());

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(dto.getUserId()));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(dto.getCategoryId()));

        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(dto, transaction);
        transaction.setUser(user);
        transaction.setCategory(category);
        transaction.setType(category.getType()); // Type always comes from category

        Transaction saved = transactionRepository.save(transaction);
        return mapToDto(saved);
    }

    /**
     * Get all transactions
     */
    @Override
    public List<DtoTransaction> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    /**
     * Delete transaction by ID
     */
    @Override
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new TransactionNotFoundException(id);
        }
        transactionRepository.deleteById(id);
    }

    /**
     * Update an existing transaction
     */
    @Override
    public DtoTransaction updateTransaction(Long id, DtoTransactionIU dto) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));

        // Update user if provided
        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new UserNotFoundException(dto.getUserId()));
            transaction.setUser(user);
        }

        // Update category if provided
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new CategoryNotFoundException(dto.getCategoryId()));
            transaction.setCategory(category);
            transaction.setType(category.getType());
        }

        // Update amount
        if (dto.getAmount() != null) {
            validateAmount(dto.getAmount());
            transaction.setAmount(dto.getAmount());
        }

        // Update date & description if provided
        if (dto.getDate() != null) transaction.setDate(dto.getDate());
        if (dto.getDescription() != null) transaction.setDescription(dto.getDescription());

        Transaction updated = transactionRepository.save(transaction);
        return mapToDto(updated);
    }

    /**
     * Get transaction by ID
     */
    @Override
    public DtoTransaction getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
        return mapToDto(transaction);
    }

    /**
     * Get transactions filtered by user, category, and date range
     */
    @Override
    public Page<DtoTransaction> getTransactionsFiltered(
            Long userId,
            Long categoryId,
            LocalDate fromDate,
            LocalDate toDate,
            Pageable pageable
    ) {
        LocalDateTime start = fromDate.atStartOfDay();
        LocalDateTime end = toDate.atTime(23, 59, 59);

        Page<Transaction> transactions;
        if (categoryId != null) {
            transactions = transactionRepository.findByUserIdAndCategoryIdAndDateBetween(
                    userId, categoryId, start, end, pageable);
        } else {
            transactions = transactionRepository.findByUserIdAndDateBetween(
                    userId, start, end, pageable);
        }

        return transactions.map(this::mapToDto);
    }

    /**
     * Calculate total transaction amount for a user in a date range
     */
    @Override
    public BigDecimal getTotalAmountByUserAndDateRange(Long userId, LocalDate fromDate, LocalDate toDate) {
        LocalDateTime start = fromDate.atStartOfDay();
        LocalDateTime end = toDate.atTime(23, 59, 59);

        List<Transaction> transactions = transactionRepository.findByUserIdAndDateBetween(userId, start, end);
        return transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Map Transaction entity to DTO
     */
    private DtoTransaction mapToDto(Transaction transaction) {
        DtoTransaction dto = new DtoTransaction();
        BeanUtils.copyProperties(transaction, dto);
        dto.setUserId(transaction.getUser().getId());
        dto.setCategoryId(transaction.getCategory().getId());
        return dto;
    }

    /**
     * Validate transaction amount
     */
    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidRequestException("Transaction amount must be greater than zero");
        }
    }
}
