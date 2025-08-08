package com.example.moneytransfer.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransferRequest {
    @NotNull(message = "Source account ID is required")
    private Long fromAccountId;
    
    @NotNull(message = "Destination account ID is required")
    private Long toAccountId;
    
    @NotNull(message = "Transfer amount is required")
    @Positive(message = "Transfer amount must be positive")
    private BigDecimal amount;
}
