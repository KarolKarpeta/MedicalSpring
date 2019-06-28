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

    User employee = employeeRepository.findByName(name);
    String surname = employee.getSurname();

    model.addAttribute("name", name);
    model.addAttribute("surname", surname);
}

}
