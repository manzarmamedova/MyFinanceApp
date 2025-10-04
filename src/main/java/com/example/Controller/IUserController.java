package com.example.Controller;

import com.example.dto.DtoUser;
import com.example.dto.DtoUserIU;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * IUserController
 *
 * Interface for User Controller operations.
 * Defines the contract for CRUD methods related to User entity.
 */
public interface IUserController {

    /**
     * Create a new user
     * @param dtoUserIU input data transfer object
     * @return created user as ResponseEntity
     */
    ResponseEntity<DtoUser> createUser(DtoUserIU dtoUserIU);

    /**
     * Get all users
     * @return list of users
     */
    ResponseEntity<List<DtoUser>> getAllUsers();

    /**
     * Update an existing user by ID
     * @param id user ID
     * @param dto input data for update
     * @return updated user
     */
    ResponseEntity<DtoUser> updateUser(Long id, DtoUserIU dto);

    /**
     * Delete a user by ID
     * @param id user ID
     * @return status message
     */
    ResponseEntity<String> deleteUser(Long id);
}


