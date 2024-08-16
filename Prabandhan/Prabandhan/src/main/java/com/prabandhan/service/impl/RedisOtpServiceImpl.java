package com.prabandhan.service.impl;

import com.prabandhan.service.RedisOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedisOtpServiceImpl implements RedisOtpService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private Map<String, String> fallbackCache = new HashMap<>();

    @Override
    public String generateOtp() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }

    @Override
    public void saveOtp(String userId, String otp) {
        String key = "otp:" + userId;
        try {
            redisTemplate.opsForValue().set(key, otp, 5, TimeUnit.MINUTES);
        } catch (Exception e) {
            fallbackCache.put(userId, otp);
        }
    }

    @Override
    public String getOtp(String userId) {
        String key = "otp:" + userId;
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            return fallbackCache.get(userId);
        }
    }

    @Override
    public void deleteOtp(String userId) {
        String key = "otp:" + userId;
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            fallbackCache.remove(userId);
        }
    }
}