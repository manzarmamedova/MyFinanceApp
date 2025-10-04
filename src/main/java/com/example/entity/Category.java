package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Category entity
 *
 * Represents a category for transactions.
 * Stored in the "category" table in the database.
 */
@Entity
@Table(name="category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    /** Category ID (primary key, auto-generated) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Name of the category */
    @Column(name="name")
    private String name;

    /** Type of the category (e.g., INCOME, EXPENSE) */
    @Enumerated(EnumType.STRING)
    private CategoryType type;
}
