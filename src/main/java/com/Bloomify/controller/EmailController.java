package com.Bloomify.controller;


import com.Bloomify.dto.OtpValidateDto;
import com.Bloomify.response.CustomApiResponse;
import com.Bloomify.service.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
@Tag(name = "Email Sender")

public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send-otp")
    public ResponseEntity<CustomApiResponse> sendOTP(@RequestBody OtpValidateDto otpValidateDto){
        return CustomApiResponse.builder().data(emailService.sendOTP(otpValidateDto)).build();
    }
}
