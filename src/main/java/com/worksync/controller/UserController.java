package com.worksync.controller;

import com.worksync.dto.ApiResponse;
import jakarta.validation.Valid;
import com.worksync.dto.UserDTO;
import com.worksync.model.User;
import com.worksync.service.UserService;
import com.worksync.dto.LoginDTO;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
public User register(@RequestBody @Valid UserDTO dto) {
    User user = new User();
    user.setName(dto.getName());
    user.setEmail(dto.getEmail());
    user.setPassword(dto.getPassword());

    return service.register(user);
}

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }
    @PostMapping("/login")
public ApiResponse login(@RequestBody @Valid LoginDTO dto) {

    User user = service.login(dto.getEmail(), dto.getPassword());

    if (user == null) {
        return new ApiResponse("Invalid email or password", null);
    }

    return new ApiResponse("Login successful", user);
}
}
