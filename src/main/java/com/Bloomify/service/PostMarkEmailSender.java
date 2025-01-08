package com.Bloomify.service;



public interface PostMarkEmailSender {
    void sendEmail(String to, String subject, String body);
}
