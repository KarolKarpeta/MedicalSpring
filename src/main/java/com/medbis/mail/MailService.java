package com.medbis.mail;

import com.medbis.entity.Employee;
import com.medbis.entity.Visit;
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

   public JavaMailSenderImpl createMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setUsername(mailCfg.getUsername());
        mailSender.setPassword(mailCfg.getPassword());
        mailSender.setHost(mailCfg.getHost());
        mailSender.setPort(Integer.parseInt(mailCfg.getPort()));
        Properties properties = mailSender.getJavaMailProperties();
        properties.setProperty("mail.smtp.starttls.enable", "true");
        return mailSender;
   }

   public SimpleMailMessage createMailMessage(String mail, MailDto mailDto){
       SimpleMailMessage mailMessage = new SimpleMailMessage();
       mailMessage.setFrom(mailCfg.getUsername());
       mailMessage.setTo(mail);
       mailMessage.setSubject(mailDto.getSubject());
       mailMessage.setText(mailDto.getMessage());
       return mailMessage;
   }

   public SimpleMailMessage createMailMessage(String mail, Visit visit, Employee employee, String action){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailCfg.getUsername());
        mailMessage.setTo(mail);

        switch(action) {
            case "deleteVisit":
                mailMessage.setSubject("Informacja o anulowaniu wizyty pielęgniarki w dn. " +
                        visit.getVisitDate() + ".");
                mailMessage.setText(createDeleteVisitMail(visit, employee));
                break;
            case "addVisit":
                mailMessage.setSubject("Biuro Medical Spring");
                mailMessage.setText(createNewVisitMail(visit, employee));
                break;
            case "editVisit":
                mailMessage.setSubject(("Informacja o zmianie terminu wizyty pielęgniarki"));
                mailMessage.setText(createEditVisitMail(visit, employee));
                break;
            default:
                System.out.println("Something with mail sending went wrong.");
        }

        return mailMessage;
    }

   public String createNewVisitMail(Visit visit, Employee employee) {
        return "Dzień dobry,\n"  +
                "Przypominamy o zaplanowanej wizycie, która odbędzie się dn. " + visit.getVisitDate() + ". " +
                "Wizytę przeprowadzi " + employee.getName() + " " + employee.getSurname() +
                " " + " W celu odwołania lub przełożenia wizyty prosimy o kontakt przynajmniej 24h przed planowaną wizytą. \n" +
                "Do zobaczenia!";
    }

    public String createDeleteVisitMail(Visit visit, Employee employee) {
        return "Dzień dobry,\n\n"  +
                "Informujemy, że wizyta pielęgniarki "+ employee.getName() + " " + employee.getSurname() +
                " zaplanowana na dzień " + visit.getVisitDate() + " została odwołana.\n" +
                "W celu umówienia nowej wizyty prosimy o kontakt z ww. pielęgniarką pod nr. telefonu: " +
                employee.getWorkPhoneNumber() +".\n\nPozdrawiamy,\nBiuro Medical Spring";
    }

    public String createEditVisitMail(Visit visit, Employee employee) {
        return "Dzień dobry,\n\n"  +
                "Informujemy, że wizyta pielęgniarki "+ employee.getName() + " " + employee.getSurname() +
                " została przełożona na dzień " + visit.getVisitDate() + ".\n" +
                "W razie pytań prosimy o kontakt z ww. pielęgniarką pod nr. telefonu: " +
                employee.getWorkPhoneNumber() +".\n\nPozdrawiamy,\nBiuro Medical Spring";
    }

}
