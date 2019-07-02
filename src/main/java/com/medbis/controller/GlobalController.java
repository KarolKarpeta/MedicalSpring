package com.medbis.controller;


import com.medbis.entity.Employee;
import com.medbis.entity.User;
import com.medbis.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@ControllerAdvice(basePackages = "com.medbis.controller")
public class GlobalController {

    private EmployeeRepository employeeRepository;

    @Autowired
    public GlobalController(EmployeeRepository employeeRepository) {
       this.employeeRepository = employeeRepository;
    }



    @ModelAttribute
    public void addLoggedUserAttribute(Model model, Authentication auth) {
        String name = auth.getName();
        Employee employee = employeeRepository.findByName(name);
        model.addAttribute("employeeId", employee.getId());
        model.addAttribute("name", employee.getName());
        model.addAttribute("surname", employee.getSurname());
        model.addAttribute("isAdmin", isAdmin(auth));
}

    private boolean isAdmin(Authentication authentication){
        Employee employee = employeeRepository.findByName(authentication.getName());
        return String.valueOf(employee.getPermissions()).contains("ADMIN");

    }


    }