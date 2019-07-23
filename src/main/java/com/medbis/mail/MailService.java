package com.medbis.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MailService {

    private MailCfg mailCfg;

    MailService(MailCfg mailCfg){
        this.mailCfg = mailCfg;
    }

   JavaMailSenderImpl createMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setUsername(mailCfg.getUsername());
        mailSender.setPassword(mailCfg.getPassword());
        mailSender.setHost(mailCfg.getHost());
        mailSender.setPort(Integer.parseInt(mailCfg.getPort()));
        Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty("mail.smtp.starttls.enable", "true");
        return mailSender;
   }

   SimpleMailMessage createMailMessage(String mail, MailDto mailDto){
       SimpleMailMessage mailMessage = new SimpleMailMessage();
       mailMessage.setFrom(mailCfg.getUsername());
       mailMessage.setTo(mail);
       mailMessage.setSubject(mailDto.getSubject());
       mailMessage.setText(mailDto.getMessage());
       return mailMessage;
   }

}

