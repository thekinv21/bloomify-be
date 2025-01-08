package com.Bloomify.service;

import com.Bloomify.dto.HtmlEmailDto;

import java.util.Map;

public interface EmailSender {

    void sendEmail(String to, String subject, String body);
    void sendHtmlEmail(String to, String subject, String body);
    void htmlSend(HtmlEmailDto HTMLTemplateDto, Map<String, Object> model);

}
