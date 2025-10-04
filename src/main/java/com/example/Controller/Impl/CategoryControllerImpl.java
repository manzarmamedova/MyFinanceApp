package com.example.Controller.Impl;

import com.example.Controller.ICategoryController;
import com.example.Services.ICategoryService;
import com.example.dto.DtoCategory;
import com.example.dto.DtoCategoryIU;
import com.example.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CategoryControllerImpl
 *
 * REST controller implementation for managing categories.
 * Provides endpoints for CRUD operations on Category entity.
 */
@RestController
@RequestMapping(path = "/category")
public class CategoryControllerImpl implements ICategoryController {

    private final ICategoryService categoryService;
    private final CategoryRepository categoryRepository;

    // Constructor Injection (recommended for immutability and testing)
    public CategoryControllerImpl(ICategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Create a new category
     * @param dto input data transfer object
     * @return created category
     */
    @Override
    @PostMapping("/save")
    public ResponseEntity<DtoCategory> createCategory(@RequestBody DtoCategoryIU dto) {
        DtoCategory category = categoryService.createCategory(dto);
        return ResponseEntity.ok(category);
    }

    /**
     * Get all categories
     * @return list of categories
     */
    @Override
    @GetMapping("/get")
    public ResponseEntity<List<DtoCategory>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    /**
     * Delete a category by ID
     * @param id category ID
     * @return status message
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        if (!categoryRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Category not found");
        }
        categoryRepository.deleteById(id);
        return ResponseEntity.ok("Category deleted successfully");
    }

    /**
     * Update an existing category by ID
     * @param id category ID
     * @param dtoCategoryIU input data for update
     * @return updated category
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<DtoCategory> updateCategory(
            @PathVariable Long id,
            @RequestBody DtoCategoryIU dtoCategoryIU) {
        DtoCategory updatedCategory = categoryService.updateCategory(id, dtoCategoryIU);
        return ResponseEntity.ok(updatedCategory);
    }

    /**
     * Get a single category by ID
     * @param id category ID
     * @return category details
     */
    @GetMapping("/{id}")
    public ResponseEntity<DtoCategory> getCategoryById(@PathVariable Long id) {
        DtoCategory dto = categoryService.getCategoryById(id);
        return ResponseEntity.ok(dto);
    }
}
