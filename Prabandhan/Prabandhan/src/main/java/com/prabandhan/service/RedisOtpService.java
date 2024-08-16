package com.prabandhan.service;

public interface RedisOtpService {
    String generateOtp();
    void saveOtp(String userId, String otp);
    String getOtp(String userId);
    void deleteOtp(String userId);
}
