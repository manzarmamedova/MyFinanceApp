package com.example.Services.Impl;

import com.example.Services.ICategoryService;
import com.example.dto.DtoCategory;
import com.example.dto.DtoCategoryIU;
import com.example.entity.Category;
import com.example.entity.CategoryType;
import com.example.entity.Transaction;
import com.example.exception.CategoryNotFoundException;
import com.example.exception.InvalidCategoryTypeException;
import com.example.exception.InvalidRequestException;
import com.example.repository.CategoryRepository;
import com.example.repository.TransactionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of Category Service
 * Handles CRUD operations for Category entity
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    // Constructor injection
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               TransactionRepository transactionRepository) {
        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * Delete category by ID
     * @param id category id
     */
    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException(id);
        }
        categoryRepository.deleteById(id);
    }

    /**
     * Create a new category
     * @param dtoCategoryIU input DTO
     * @return created category as DTO
     */
    @Override
    public DtoCategory createCategory(DtoCategoryIU dtoCategoryIU) {
        if (dtoCategoryIU.getName() == null || dtoCategoryIU.getName().isBlank()) {
            throw new InvalidRequestException("Category name cannot be empty");
        }

        Category category = new Category();
        category.setName(dtoCategoryIU.getName());

        // Convert type string to enum
        if (dtoCategoryIU.getType() != null) {
            try {
                CategoryType newType = CategoryType.valueOf(dtoCategoryIU.getType());
                category.setType(newType);
            } catch (IllegalArgumentException e) {
                throw new InvalidCategoryTypeException(dtoCategoryIU.getType());
            }
        }

        Category savedCategory = categoryRepository.save(category);

        DtoCategory dto = new DtoCategory();
        dto.setId(savedCategory.getId());
        dto.setName(savedCategory.getName());
        dto.setType(savedCategory.getType());
        return dto;
    }

    /**
     * Update an existing category
     * @param id category id
     * @param dtoCategoryIU input DTO
     * @return updated category as DTO
     */
    @Override
    public DtoCategory updateCategory(Long id, DtoCategoryIU dtoCategoryIU) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        // Update name
        if (dtoCategoryIU.getName() != null && !dtoCategoryIU.getName().isBlank()) {
            category.setName(dtoCategoryIU.getName());
        }

        // Update type
        if (dtoCategoryIU.getType() != null) {
            try {
                CategoryType newType = CategoryType.valueOf(dtoCategoryIU.getType());

                // If type changed, update transactions
                if (!newType.equals(category.getType())) {
                    category.setType(newType);

                    List<Transaction> transactions = transactionRepository.findByCategory(category);
                    for (Transaction t : transactions) {
                        t.setType(newType);
                    }
                    transactionRepository.saveAll(transactions);
                }
            } catch (IllegalArgumentException e) {
                throw new InvalidCategoryTypeException(dtoCategoryIU.getType());
            }
        }

        Category updatedCategory = categoryRepository.save(category);

        DtoCategory dto = new DtoCategory();
        dto.setId(updatedCategory.getId());
        dto.setName(updatedCategory.getName());
        dto.setType(updatedCategory.getType());
        return dto;
    }

    /**
     * Get all categories
     * @return list of category DTOs
     */
    @Override
    public List<DtoCategory> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> {
                    DtoCategory dto = new DtoCategory();
                    BeanUtils.copyProperties(category, dto);
                    return dto;
                })
                .toList();
    }

    /**
     * Get category by ID
     * @param id category id
     * @return category DTO
     */
    @Override
    public DtoCategory getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        DtoCategory dto = new DtoCategory();
        BeanUtils.copyProperties(category, dto);
        return dto;
    }

}
