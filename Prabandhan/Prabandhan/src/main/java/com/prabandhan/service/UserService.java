package com.prabandhan.service;

import com.prabandhan.model.LoginRequest;
import com.prabandhan.model.User;

public interface UserService {
    User register(User user);
    String login(LoginRequest loginRequest);
    User getUserProfile(Long userId);
    User updateUserProfile(Long userId, User user);
}
