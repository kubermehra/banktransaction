package com.example.moneytransfer.repository;

import com.example.moneytransfer.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFromAccountIdOrToAccountIdOrderByTimestampDesc(Long fromAccountId, Long toAccountId);
}
