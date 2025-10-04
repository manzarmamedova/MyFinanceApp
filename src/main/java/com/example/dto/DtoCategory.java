package com.example.dto;

import com.example.entity.CategoryType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DtoCategory
 *
 * Data Transfer Object for Category entity.
 * Used for sending category data between client and server.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCategory {

    /** Category ID */
    private long id;

    /** Category name */
    private String name;

    /** Category type (e.g., INCOME, EXPENSE) */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private CategoryType type;
}
