package com.example.moneytransfer.service;

import com.example.moneytransfer.dto.TransactionResponse;
import com.example.moneytransfer.dto.TransferRequest;
import com.example.moneytransfer.exception.InsufficientFundsException;
import com.example.moneytransfer.model.Transaction;
import com.example.moneytransfer.model.UserAccount;
import com.example.moneytransfer.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;

    public TransactionService(TransactionRepository transactionRepository, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    @Transactional
    public TransactionResponse transfer(TransferRequest request) {
        UserAccount fromAccount = userService.getUserById(request.getFromAccountId());
        UserAccount toAccount = userService.getUserById(request.getToAccountId());

        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient funds in source account");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));

        Transaction transaction = new Transaction();
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setAmount(request.getAmount());

        Transaction savedTransaction = transactionRepository.save(transaction);
        return convertToResponse(savedTransaction);
    }

    public List<TransactionResponse> getTransactionHistory(Long userId) {
        return transactionRepository
                .findByFromAccountIdOrToAccountIdOrderByTimestampDesc(userId, userId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private TransactionResponse convertToResponse(Transaction transaction) {
        TransactionResponse response = new TransactionResponse();
        response.setId(transaction.getId());
        response.setFromAccountId(transaction.getFromAccount().getId());
        response.setToAccountId(transaction.getToAccount().getId());
        response.setAmount(transaction.getAmount());
        response.setTimestamp(transaction.getTimestamp());
        return response;
    }
}
