package com.prabandhan.service.impl;

import com.prabandhan.service.SmsService;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {
    @Override
    public void sendSms(String phoneNumber, String message) {
        // Implement SMS sending logic here, using an external API or service
    }
}