package com.example.bookrentalsystem.entity;

import com.example.bookrentalsystem.util.Role;
import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;
}
