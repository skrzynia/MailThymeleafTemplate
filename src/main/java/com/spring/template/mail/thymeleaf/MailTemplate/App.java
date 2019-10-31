package com.spring.template.mail.thymeleaf.MailTemplate;

import com.spring.template.mail.thymeleaf.MailTemplate.model.Mail;
import com.spring.template.mail.thymeleaf.MailTemplate.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class App implements ApplicationRunner {

    private static Logger logger = LoggerFactory.getLogger(App.class);
    @Autowired
    private EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

        logger.info("Sending email");

        Mail mail = new Mail();
        mail.setFrom("no-reply@wojtek.com");
        mail.setTo("");
        mail.setSubject("Sending email");

        Map model = new HashMap();
        model.put("name" ,"Your Name");
        model.put("location","NYC");
        model.put("signature","Your Signature");
        mail.setModel(model);

        emailService.sendMessage(mail);
    }
}
