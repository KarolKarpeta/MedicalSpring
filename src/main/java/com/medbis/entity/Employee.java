package com.medbis.entity;


import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Min;
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
    @Min(value = 8)
    private String password;

    @Column(name="login")
    @NotEmpty
    private String login;

    @Column(name="status")
    private boolean status;




    public String getPermissions() {
        return permissions;
    }

    private String permissions;

    public Employee(String password, @NotEmpty String login, String permissions) {
        this.password = password;
        this.login = login;
        this.status = false;
        this.permissions = permissions;

    }

    public Employee() {

    }

    public List<String> getPermissionsList() throws NullPointerException {
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


    public boolean getStatus() {
        return status;
    }

    public void setStatusAfterPasswordChange() {
        this.status = true;
    }
}
