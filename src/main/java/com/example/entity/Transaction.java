package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Transaction entity
 *
 * Represents a financial transaction in the system.
 * Stored in the "transaction" table in the database.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    /** Transaction ID (primary key, auto-generated) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Amount of the transaction */
    private BigDecimal amount;

    /** Type of the transaction (INCOME or EXPENSE) */
    @Enumerated(EnumType.STRING)
    private CategoryType type;

    /** Date and time of the transaction */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;

    /** Description or note for the transaction */
    private String description;

    /** User associated with the transaction */
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    /** Category associated with the transaction */
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;
}
