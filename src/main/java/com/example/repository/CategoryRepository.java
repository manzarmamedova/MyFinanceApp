package com.example.repository;

import com.example.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CategoryRepository
 *
 * Repository interface for Category entity.
 * Provides CRUD operations and integrates with Spring Data JPA.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // No additional methods needed for basic CRUD
}
