package com.example.moneytransfer.controller;

import com.example.moneytransfer.dto.CreateUserRequest;
import com.example.moneytransfer.dto.TransactionResponse;
import com.example.moneytransfer.dto.TransferRequest;
import com.example.moneytransfer.model.UserAccount;
import com.example.moneytransfer.service.TransactionService;
import com.example.moneytransfer.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MoneyTransferController {
    private final UserService userService;
    private final TransactionService transactionService;

    public MoneyTransferController(UserService userService, TransactionService transactionService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserAccount> createUser(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PostMapping("/transactions/transfer")
    public ResponseEntity<TransactionResponse> transferMoney(@Valid @RequestBody TransferRequest request) {
        return ResponseEntity.ok(transactionService.transfer(request));
    }

    @GetMapping("/transactions/{userId}")
    public ResponseEntity<List<TransactionResponse>> getTransactionHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.getTransactionHistory(userId));
    }
}
