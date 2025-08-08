package com.example.moneytransfer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CreateUserRequest {
    @NotBlank(message = "Name is required")
    private String name;
    
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
    
    @NotNull(message = "Initial balance is required")
    @Positive(message = "Initial balance must be positive")
    private BigDecimal initialBalance;
}
