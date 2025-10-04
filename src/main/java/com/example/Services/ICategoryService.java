package com.example.Services;

import com.example.dto.DtoCategory;
import com.example.dto.DtoCategoryIU;

import java.util.List;

/**
 * Service interface for Category operations.
 * Defines methods for CRUD operations and fetching categories.
 */
public interface ICategoryService {

   /**
    * Create a new category
    * @param dtoCategoryIU the data transfer object containing category info
    * @return the created category as DTO
    */
   DtoCategory createCategory(DtoCategoryIU dtoCategoryIU);

   /**
    * Get all categories
    * @return list of category DTOs
    */
   List<DtoCategory> getAllCategories();

   /**
    * Delete a category by ID
    * @param id the ID of the category to delete
    */
   void deleteCategory(Long id);

   /**
    * Update an existing category
    * @param id the ID of the category to update
    * @param dtoCategoryIU the DTO containing updated information
    * @return the updated category as DTO
    */
   DtoCategory updateCategory(Long id, DtoCategoryIU dtoCategoryIU);

   /**
    * Get category by ID
    * @param id the ID of the category
    * @return the category as DTO
    */
   DtoCategory getCategoryById(Long id);
}
