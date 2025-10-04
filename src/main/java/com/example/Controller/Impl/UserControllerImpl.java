package com.example.Controller.Impl;

import com.example.Controller.IUserController;
import com.example.Services.IUserService;
import com.example.dto.DtoUser;
import com.example.dto.DtoUserIU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserControllerImpl
 *
 * REST controller implementation for managing users.
 * Provides endpoints for CRUD operations and fetching user by ID.
 */
@RestController
@RequestMapping(path="/user")
public class UserControllerImpl implements IUserController {

    @Autowired
    private IUserService userService;

    /**
     * Create a new user
     * @param dtoUserIU input data transfer object
     * @return created user
     */
    @Override
    @PostMapping("/save")
    public ResponseEntity<DtoUser> createUser(@RequestBody DtoUserIU dtoUserIU) {
        return ResponseEntity.ok(userService.createUser(dtoUserIU));
    }

    /**
     * Get all users
     * @return list of users
     */
    @Override
    @GetMapping("/get")
    public ResponseEntity<List<DtoUser>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Update an existing user by ID
     * @param id user ID
     * @param dtoUserIU input data for update
     * @return updated user
     */
    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<DtoUser> updateUser(@PathVariable Long id, @RequestBody DtoUserIU dtoUserIU) {
        return ResponseEntity.ok(userService.updateUser(id, dtoUserIU));
    }

    /**
     * Delete a user and all their transactions
     * @param id user ID
     * @return status message
     */
    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User and all associated transactions have been deleted");
    }

    /**
     * Get a single user by ID
     * @param id user ID
     * @return user details
     */
    @GetMapping("/{id}")
    public ResponseEntity<DtoUser> getUserById(@PathVariable Long id) {
        DtoUser dto = userService.getUserById(id);
        return ResponseEntity.ok(dto);
    }
}
