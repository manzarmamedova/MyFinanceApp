package com.example.Services;

import com.example.dto.DtoUser;
import com.example.dto.DtoUserIU;
import com.example.entity.User;

import java.util.List;

/**
 * Service interface for managing users.
 */
public interface IUserService {

    /**
     * Creates a new user.
     *
     * @param dtoUserIU user data from client
     * @return created user as DTO
     */
    DtoUser createUser(DtoUserIU dtoUserIU);

    /**
     * Retrieves all users.
     *
     * @return list of all users as DTOs
     */
    List<DtoUser> getAllUsers();

    /**
     * Updates an existing user.
     *
     * @param id user ID
     * @param dtoUserIU updated user data
     * @return updated user as DTO
     */
    DtoUser updateUser(Long id, DtoUserIU dtoUserIU);

    /**
     * Deletes a user by ID.
     *
     * @param id user ID
     */
    void deleteUser(Long id);

    /**
     * Retrieves a user by ID.
     *
     * @param id user ID
     * @return user as DTO
     */
    DtoUser getUserById(Long id);
}
