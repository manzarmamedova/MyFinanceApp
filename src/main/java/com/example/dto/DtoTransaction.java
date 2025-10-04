package com.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DtoTransaction
 *
 * Data Transfer Object for Transaction entity.
 * Used for sending transaction data between client and server.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoTransaction {

    /** ID of the user associated with the transaction */
    private Long userId;

    /** ID of the category associated with the transaction */
    private Long categoryId;

    /** Amount of the transaction */
    private BigDecimal amount;

    /** Date and time of the transaction */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;

    /** Description or note for the transaction */
    private String description;
}



