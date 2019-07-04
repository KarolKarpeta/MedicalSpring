package com.medbis.dto;

import org.springframework.stereotype.Component;

@Component
public class PasswordChangerDto {

    private String  newPassword;
    private String confirmedPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
}
