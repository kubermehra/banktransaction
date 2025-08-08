package com.example.moneytransfer.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private UserAccount fromAccount;
    
    @ManyToOne
    private UserAccount toAccount;
    
    private BigDecimal amount;
    
    private LocalDateTime timestamp;
    
    @PrePersist
    public void prePersist() {
        timestamp = LocalDateTime.now();
    }
}
