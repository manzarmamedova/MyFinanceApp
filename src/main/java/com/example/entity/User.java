package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * User entity
 *
 * Represents a user in the system.
 * Stored in the "user" table in the database.
 */
@Entity
@Table(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /** User ID (primary key, auto-generated) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Username */
    @Column(name="user_name")
    private String userName;

    /** User password */
    @Column(name="password")
    private String password;

    /** User email */
    @Column(name="email")
    private String email;

    /** Role of the user (e.g., ADMIN, USER) */
    @Enumerated(EnumType.STRING)
    private Role role;

    /** List of transactions associated with the user */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Transaction> transactions;
}
