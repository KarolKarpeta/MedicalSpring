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
    @Autowired
    EmployeeRepository employeeRepository;

    @ModelAttribute
    public void addLoggedUserAttribute(Model model, Authentication auth) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    String name = auth.getName();
    Employee employee = employeeRepository.findByName(name);
    String surname = (String) employee.getClass().getSuperclass().getMethod("getSurname").invoke(employee);
    model.addAttribute("name", name);
    model.addAttribute("surname", surname);
 /*   model.addAttribute("authentication", auth);*/
}

}
