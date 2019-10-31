package com.spring.template.mail.thymeleaf.MailTemplate.service;

import com.spring.template.mail.thymeleaf.MailTemplate.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
   private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    public void sendMessage(Mail mail) throws MessagingException, IOException {
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        messageHelper.addAttachment("blablabla.png",new ClassPathResource("blablabla.png"));

        Context context = new Context();
        context.setVariables(mail.getModel());

        String html = springTemplateEngine.process("email-template",context);

        messageHelper.setTo(mail.getTo());
        messageHelper.setFrom(mail.getFrom());
        messageHelper.setText(html,true);
        messageHelper.setSubject(mail.getSubject());

        javaMailSender.send(mailMessage);

    }


}
