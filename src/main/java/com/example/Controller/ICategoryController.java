package com.example.Controller;

import com.example.dto.DtoCategory;
import com.example.dto.DtoCategoryIU;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * ICategoryController
 *
 * Interface for Category Controller operations.
 * Defines the contract for CRUD methods related to Category entity.
 */
public interface ICategoryController {

    /**
     * Create a new category
     * @param dtoCategoryIU input data transfer object
     * @return created category as ResponseEntity
     */
    ResponseEntity<DtoCategory> createCategory(DtoCategoryIU dtoCategoryIU);

    /**
     * Get all categories
     * @return list of categories
     */
    ResponseEntity<List<DtoCategory>> getAllCategories();

    /**
     * Delete category by ID
     * @param id category ID
     * @return status message
     */
    ResponseEntity<String> deleteCategory(Long id);

    /**
     * Update category by ID
     * @param id category ID
     * @param dtoCategoryIU input data for update
     * @return updated category
     */
    ResponseEntity<DtoCategory> updateCategory(Long id, DtoCategoryIU dtoCategoryIU);
}
