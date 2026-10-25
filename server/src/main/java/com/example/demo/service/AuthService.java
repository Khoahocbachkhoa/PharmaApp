package com.example.demo.service;

import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.Account;
import com.example.demo.exception.InvalidLoginException;
import com.example.demo.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AccountRepository accountRepository;

    public AuthService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Account account = accountRepository
                .findByUsername(loginRequest.getUsername())
                .orElseThrow(
                        () -> new InvalidLoginException("User Notfound"));

        if (!account.getPassword().equals(loginRequest.getPassword())) {
            throw new InvalidLoginException("Incorrect password");
        }

        return new LoginResponse("true");
    }
}
