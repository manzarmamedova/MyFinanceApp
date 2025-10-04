package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DtoTransactionIU
 *
 * Data Transfer Object for creating or updating a Transaction.
 * Used for input when sending transaction data from client to server.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoTransactionIU {

    /** ID of the user associated with the transaction */
    private Long userId;

    /** ID of the category associated with the transaction */
    private Long categoryId;

    /** Amount of the transaction */
    private BigDecimal amount;

    /** Date and time of the transaction */
    private LocalDateTime date;

    /** Description or note for the transaction */
    private String description;
}
