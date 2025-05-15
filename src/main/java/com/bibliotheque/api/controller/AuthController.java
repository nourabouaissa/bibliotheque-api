package com.bibliotheque.api.controller;

import com.bibliotheque.api.dto.*;
import com.bibliotheque.api.model.User;
import com.bibliotheque.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.bibliotheque.api.security.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;


    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody RegisterRequest request) {
        User user = userService.register(request.getUsername(), request.getPassword(), request.getRole());
        UserResponseDto response = new UserResponseDto(user.getId(), user.getUsername(), user.getRole());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponseDto> response = users.stream()
                .map(user -> new UserResponseDto(user.getId(), user.getUsername(), user.getRole()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (!userService.authenticate(request.getUsername(), request.getPassword())) {
            return ResponseEntity.status(401).body("Nom d'utilisateur ou mot de passe incorrect");
        }

        UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(token));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);

        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
