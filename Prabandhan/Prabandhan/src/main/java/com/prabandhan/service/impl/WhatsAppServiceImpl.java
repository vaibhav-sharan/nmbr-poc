package com.prabandhan.service.impl;

import com.prabandhan.service.WhatsAppService;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppServiceImpl implements WhatsAppService {
    @Override
    public void sendWhatsAppMessage(String number, String message) {
        // Implement WhatsApp message sending logic here, using an external API or service
    }
}