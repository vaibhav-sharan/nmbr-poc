package com.prabandhan.service.impl;

import com.prabandhan.model.LoginRequest;
import com.prabandhan.model.User;
import com.prabandhan.repository.UserRepository;
import com.prabandhan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        // Implement password check and token generation logic
        return "dummy-token"; // Replace with actual token generation
    }

    @Override
    public User getUserProfile(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateUserProfile(Long userId, User user) {
        User existingUser = getUserProfile(userId);
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }
}
