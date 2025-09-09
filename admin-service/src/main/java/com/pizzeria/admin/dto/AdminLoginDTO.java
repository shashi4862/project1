package com.pizzeria.admin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * A Data Transfer Object for handling admin login requests.
 * It contains only the necessary fields (email and password) to prevent
 * exposing other sensitive information.
 */
@Data
public class AdminLoginDTO {

    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}
