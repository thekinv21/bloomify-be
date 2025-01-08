package com.Bloomify.service.impl;

import com.Bloomify.dto.HtmlEmailDto;
import com.Bloomify.service.EmailSender;
import com.Bloomify.service.PostMarkEmailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import org.thymeleaf.context.Context;
import java.util.Map;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmailSenderImpl implements EmailSender {

    private final PostMarkEmailSender postmarkEmailSender;
    private final SpringTemplateEngine templateEngine;

    @Override
    public void sendEmail(String to, String subject, String body) {
        postmarkEmailSender.sendEmail(to, subject, body);
    }

    @Override
    public void sendHtmlEmail(String to, String subject, String body) {
        postmarkEmailSender.sendEmail(to, subject, body);
    }

    @Override
    public void htmlSend(HtmlEmailDto HTMLTemplateDto, Map<String, Object> model) {
        Context context = new Context();
        context.setVariables(model);

        String templateName = HTMLTemplateDto.templateName();
        String to = HTMLTemplateDto.to();
        String subject = HTMLTemplateDto.subject();

        String processedHtml = templateEngine.process(templateName, context);
        log.debug("Processed HTML: {}", processedHtml);
        sendHtmlEmail(to, subject, processedHtml);
    }
}
