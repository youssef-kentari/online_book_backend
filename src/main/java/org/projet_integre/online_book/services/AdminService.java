package org.projet_integre.online_book.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private PasswordEncoder passwordEncoder;

    public AdminService(
        PasswordEncoder passwordEncoder
        ) {
        this.passwordEncoder = passwordEncoder;
    }

    public String hashPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }
}
