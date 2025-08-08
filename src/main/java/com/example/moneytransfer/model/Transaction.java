package com.example.moneytransfer.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private UserAccount fromAccount;
    private UserAccount toAccount;
    private BigDecimal amount;
    private LocalDateTime timestamp;

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAccount getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(UserAccount fromAccount) {
        this.fromAccount = fromAccount;
    }

    public UserAccount getToAccount() {
        return toAccount;
    }

    public void setToAccount(UserAccount toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setInitialTimestamp() {
        this.timestamp = LocalDateTime.now();
    }
}
