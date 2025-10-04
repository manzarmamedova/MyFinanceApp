package com.example.dto;

import com.example.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DtoUserIU
 *
 * Data Transfer Object for creating or updating a User.
 * Used for input when sending user data from client to server.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoUserIU {

    /** Username */
    private String userName;

    /** User email */
    private String email;

    /** Role of the user (e.g., ADMIN, USER) */
    private Role role;
}
