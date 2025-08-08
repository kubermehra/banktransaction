package com.example.moneytransfer.service;

import com.example.moneytransfer.dto.CreateUserRequest;
import com.example.moneytransfer.model.UserAccount;
import com.example.moneytransfer.repository.UserAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserAccountRepository userAccountRepository;

    public UserService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Transactional
    public UserAccount createUser(CreateUserRequest request) {
        if (userAccountRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setName(request.getName());
        userAccount.setEmail(request.getEmail());
        userAccount.setBalance(request.getInitialBalance());

        return userAccountRepository.save(userAccount);
    }

    public UserAccount getUserById(Long id) {
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
