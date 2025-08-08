package com.example.moneytransfer.repository;

import com.example.moneytransfer.model.Transaction;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Transaction save(Transaction transaction) {
        if (transaction.getId() == null) {
            transaction.setId(idCounter.incrementAndGet());
            transaction.setInitialTimestamp();
        }
        transactions.add(transaction);
        return transaction;
    }

    public List<Transaction> findByFromAccountIdOrToAccountIdOrderByTimestampDesc(Long userId) {
        return transactions.stream()
                .filter(t -> t.getFromAccount().getId().equals(userId) || 
                           t.getToAccount().getId().equals(userId))
                .sorted((t1, t2) -> t2.getTimestamp().compareTo(t1.getTimestamp()))
                .collect(Collectors.toList());
    }
}
