package com.medbis.mail;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.Properties;

@AllArgsConstructor
@RestController
public class MailController {

    private  Mail mail;
    private MailService mailService;

    @GetMapping("/mails/send-mail")
    public void sendRemindMail() {
       JavaMailSenderImpl mailSender = mailService.creeteMailSender();
       Properties properties = mailSender.getJavaMailProperties();
       properties.setProperty("mail.smtp.starttls.enable", "true");
       SimpleMailMessage mailMessage = mailService.createMail(mail);

       mailSender.send(mailMessage);
    }


}

