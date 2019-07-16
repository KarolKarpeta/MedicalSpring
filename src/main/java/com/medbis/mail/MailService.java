package com.medbis.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class MailService {


    private MailConfig mailConfig;
    private Mail mail;

    @Autowired
    public MailService(MailConfig mailConfig, Mail mail) {
        this.mailConfig = mailConfig;
        this.mail = mail;
    }

    JavaMailSenderImpl creeteMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(this.mailConfig.getHost());
        sender.setPort(this.mailConfig.getPort());
        sender.setUsername(mailConfig.getUsername());
        sender.setPassword(this.mailConfig.getPassword());
        return sender;
    }

    SimpleMailMessage createMail(Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mail.getMail());
        mailMessage.setTo("mail@gmail.com");
        mailMessage.setSubject("Subject Example");
        mailMessage.setText("text example");
        return mailMessage;
    }
}
