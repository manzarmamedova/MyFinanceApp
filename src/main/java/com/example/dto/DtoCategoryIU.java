package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DtoCategoryIU
 *
 * Data Transfer Object for creating or updating a Category.
 * Used for input when sending data from client to server.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoCategoryIU {

    /** Name of the category */
    private String name;

    /** Type of the category (e.g., INCOME, EXPENSE) */
    private String type;
}
