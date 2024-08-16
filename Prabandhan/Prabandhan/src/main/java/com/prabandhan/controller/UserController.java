package com.prabandhan.controller;


import com.prabandhan.model.LoginRequest;
import com.prabandhan.model.User;
import com.prabandhan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

        @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = userService.register(user);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        String token = userService.login(loginRequest);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserProfile(@PathVariable Long userId) {
        User user = userService.getUserProfile(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserProfile(@PathVariable Long userId, @RequestBody User user) {
        User updatedUser = userService.updateUserProfile(userId, user);
        return ResponseEntity.ok(updatedUser);
    }
}
