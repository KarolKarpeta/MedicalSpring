package com.medbis.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
@Entity
@Table(name = "employees", schema = "public")
public class Employee extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int id;

    @Column(name="password")
    @Pattern(regexp = "((?=.*[a-z])(?=.*\\\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})",message = "illegal sign used or password is too short")
    private String password;

    @Column(name="login")
    @NotEmpty
    private String login;

    private boolean status;


    private String permissions;

    public Employee(String password, @NotEmpty String login, boolean status, String roles, String permissions) {
        this.password = password;
        this.login = login;
        this.status = status;
        this.permissions = permissions;

    }

    public Employee() {

    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getPermissions() throws NullPointerException {
        try{
           return Arrays.asList(permissions.split(","));
        }
        catch (NullPointerException err){
            return new ArrayList<String>();
        }
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
