package com.worksync.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.worksync.model.User;
import com.worksync.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;

    // ✅ UPDATED CONSTRUCTOR
    public UserService(UserRepository repo, BCryptPasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    // ✅ ENCRYPT PASSWORD
    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // ✅ FIXED LOGIN
    public User login(String email, String password) {
        return repo.findAll()
                .stream()
                .filter(u -> u.getEmail().equals(email) && encoder.matches(password, u.getPassword()))
                .findFirst()
                .orElse(null);
    }
}