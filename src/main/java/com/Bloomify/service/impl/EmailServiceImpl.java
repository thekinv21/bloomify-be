package com.Bloomify.service.impl;


import com.Bloomify.dto.OtpValidateDto;
import com.Bloomify.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Override
    public boolean sendOTP(OtpValidateDto otpValidateDto) {
        return false;
    }
}
