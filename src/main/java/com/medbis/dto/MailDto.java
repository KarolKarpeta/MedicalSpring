package com.medbis.dto;

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
