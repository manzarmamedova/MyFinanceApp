package com.example.security;

import lombok.Data;

/**
 * DTO for login request.
 * Contains username and password for authentication.
 */
@Data
public class AuthRequest {
    private String username;
    private String password;
}
