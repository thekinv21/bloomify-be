package com.Bloomify.service.impl;

import com.Bloomify.config.PostmarkClientConfig;
import com.Bloomify.service.PostMarkEmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class PostMarkEmailSenderImpl implements PostMarkEmailSender {

    @Value("${PostMark.API_KEY}")
    private String apiKey;

    @Value("${PostMark.FROM_EMAIL}")
    private String mailAddress;

    private final PostmarkClientConfig postmarkClient;


    @Override
    public void sendEmail(String to, String subject, String body) {
        Map<String, Object> emailPayload = new HashMap<>();
        emailPayload.put("From", mailAddress);
        emailPayload.put("To", to);
        emailPayload.put("Subject", subject);
        emailPayload.put("HtmlBody", body);

        postmarkClient.sendEmail(
                "application/json",
                "application/json",
                apiKey,
                emailPayload
        );
    }
}
