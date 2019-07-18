package com.medbis.mail;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class MailDto {
    private String subject;
    private String message;

}
