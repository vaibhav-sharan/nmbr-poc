package com.prabandhan.controller;

import com.prabandhan.service.RedisOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/otp")
public class RedisOtpController {

    @Autowired
    private RedisOtpService otpService;

    @PostMapping("/generate_otp")
    public Map<String, Object> generateOtp(@RequestBody Map<String, String> request) {
        String userId = request.get("user_id");
        if (userId == null) {
            return createResponse("error", "User identifier is required", 400);
        }

        String otp = otpService.generateOtp();
        otpService.saveOtp(userId, otp);

        return createResponse("message", "OTP generated successfully", 200, "otp", otp);
    }

    @PostMapping("/validate_otp")
    public Map<String, Object> validateOtp(@RequestBody Map<String, String> request) {
        String userId = request.get("user_id");
        String otp = request.get("otp");

        if (userId == null || otp == null) {
            return createResponse("error", "User identifier and OTP are required", 400);
        }

        String storedOtp = otpService.getOtp(userId);
        if (storedOtp == null) {
            return createResponse("error", "OTP has expired or is invalid", 400);
        }

        if (storedOtp.equals(otp)) {
            otpService.deleteOtp(userId);
            return createResponse("message", "OTP is valid", 200);
        } else {
            return createResponse("error", "OTP is invalid", 400);
        }
    }

    private Map<String, Object> createResponse(String key, String value, int status) {
        Map<String, Object> response = new HashMap<>();
        response.put(key, value);
        response.put("status", status);
        return response;
    }

    private Map<String, Object> createResponse(String key1, String value1, int status, String key2, String value2) {
        Map<String, Object> response = new HashMap<>();
        response.put(key1, value1);
        response.put(key2, value2);
        response.put("status", status);
        return response;
    }
}