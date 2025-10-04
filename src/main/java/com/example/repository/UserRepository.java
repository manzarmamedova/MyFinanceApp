package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository
 *
 * Repository interface for User entity.
 * Provides CRUD operations and additional query methods for finding users.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by username
     * @param userName the username to search for
     * @return an Optional containing the user if found, empty otherwise
     */
    Optional<User> findByUserName(String userName);
}
