package com.example.moneytransfer.repository;

import com.example.moneytransfer.model.UserAccount;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserAccountRepository {
    private final List<UserAccount> users = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();

    public UserAccount save(UserAccount user) {
        if (user.getId() == null) {
            user.setId(idCounter.incrementAndGet());
        }
        users.add(user);
        return user;
    }

    public Optional<UserAccount> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public boolean existsByEmail(String email) {
        return users.stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }
}
