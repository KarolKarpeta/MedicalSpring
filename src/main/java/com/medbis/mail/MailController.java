package com.medbis.mail;

import com.medbis.controller.PatientController;
import com.medbis.entity.Patient;
import com.medbis.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

@RequestMapping(
        method={RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT}
)
@Controller
public class MailController {

    private Mail mail;
    private MailService mailService;
    private UserService userService;


    //czy jak daje tak service to pole powinno byc nazwane userservce czy patient service
    public MailController(Mail mail, MailService mailService, @Qualifier(value = "PatientServiceImpl")UserService userService, PatientController patientController) {
        this.mail = mail;
        this.mailService = mailService;
        this.userService = userService;
    }

/*
        w tym momencie za kazdy przejsciem na  strone powstaje nowe mailsender, przerobic tak by byl jeden chyba ze co innego sie utaiwa - nie sprawdzalem
    */



    @GetMapping("/mails/show-mail-form")
    public String  sendMail(@RequestParam("patientId")String id, Model model) {
       Patient patient = (Patient) userService.findById(Integer.parseInt(id));
       model.addAttribute("mailDto", new MailDto());
       model.addAttribute("patient", patient);
       return "mails/mail-form";
    }

    @PostMapping(value = "/mails/send-mail")
    public String sendMail(@ModelAttribute(value = "mailDto")MailDto mailDto, BindingResult bindingResult, @PathVariable("patientId") String patientId) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        Patient patient = (Patient) userService.findById(Integer.parseInt(patientId));
        String subject = mailDto.getSubject();
        String msg = mailDto.getMessage();
        String patientMail = patient.getMail();

        JavaMailSenderImpl mailSender = mailService.createMailSender();
        Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty("mail.smtp.starttls.enable", "true");

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(mail.getMail());
        mailMessage.setTo(patientMail);
        mailMessage.setSubject(subject);
        mailMessage.setText(msg);

        mailSender.send(mailMessage);
        return "/patients";
    }
}

