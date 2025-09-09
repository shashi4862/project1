package com.pizzeria.admin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username cannot be blank")
    private String username;
    
    @NotBlank(message = "Password cannot be blank")
    private String password;
    
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

}
