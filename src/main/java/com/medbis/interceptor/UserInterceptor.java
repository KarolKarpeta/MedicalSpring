package com.medbis.interceptor;

import com.medbis.controller.EmployeeController;
import com.medbis.dto.PasswordChangerDto;
import com.medbis.entity.Employee;
import com.medbis.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class UserInterceptor implements HandlerInterceptor {

    private EmployeeServiceImpl employeeService;
    private EmployeeController employeeController;
    private PasswordChangerDto passwordChangerDto;


    @Autowired
    public UserInterceptor(EmployeeServiceImpl employeeService, EmployeeController employeeController, PasswordChangerDto passwordChangerDto) {
        this.employeeService = employeeService;
        this.employeeController = employeeController;
        this.passwordChangerDto = passwordChangerDto;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                            @Nullable ModelAndView modelAndView) {
        String employeeName = request.getUserPrincipal().getName();
        Employee employee = (Employee) employeeService.findByName(employeeName);
        if(!employeeService.checkIfPasswordIsChanged(employee)){
            try {
                response.sendRedirect("/employees/change-password-form");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }





}
