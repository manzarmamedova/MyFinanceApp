package com.example.dto;

import com.example.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DtoUser
 *
 * Data Transfer Object for User entity.
 * Used for sending user data between client and server.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoUser {

    /** User ID */
    private Long id;

    /** Username */
    private String userName;

    /** User email */
    private String email;

    /** Role of the user (e.g., ADMIN, USER) */
    private Role role;
}
