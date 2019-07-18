package com.medbis.mail;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Component
@Getter
@Setter
public class Mail {

    @NotNull
    private int id;

    @NotNull
    @Email
    private String mail;

    @NotEmpty
    private String message;
}
