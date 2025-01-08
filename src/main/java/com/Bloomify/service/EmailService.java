package com.Bloomify.service;

import com.Bloomify.dto.OtpValidateDto;

public interface EmailService {
    boolean sendOTP(OtpValidateDto otpValidateDto);
}
