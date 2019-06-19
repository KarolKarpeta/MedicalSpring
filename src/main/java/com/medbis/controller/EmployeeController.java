package com.medbis.controller;

import com.medbis.entity.Employee;
import com.medbis.entity.User;
import com.medbis.factory.UserFactory;
import com.medbis.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class EmployeeController {

    private UserService userService;
    private UserFactory userFactory;

    @Autowired
    public EmployeeController(@Qualifier(value = "EmployeeServiceImpl") UserService userService, UserFactory userFactory) {
        this.userService = userService;
        this.userFactory = userFactory;
    }

    @GetMapping("/employees")
    public String findAll(Model theModel){
        theModel.addAttribute("EmployeeList", userService.findAll());
        return "users/employee-list";
    }

    //ADDING NEW Employee
    @GetMapping("/employees/showFormForAddEmployee")
    public String showFormForAddEmployee(Model theModel){
        theModel.addAttribute("Employee", userFactory.getNewUser("employee"));
        return "users/employee-form";
    }


    @PostMapping("/employees/addNewEmployee")
    public String addNewEmployee(
            @Valid @ModelAttribute("employee") Employee theEmployee,
            BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "users/employee-form";
        }else{
            userService.save(theEmployee);
            return "redirect:/employees";
        }
    }

    //EDITING NEW Employee
    @GetMapping ("/employees/showFormForEditEmployee")
    public String showFormForEditMedicine(@RequestParam("employeeIdToEdit")int theId,
                                          Model theModel){
        Employee newEmployee = (Employee) userService.findById(theId);
        theModel.addAttribute("employee", newEmployee);
        return "users/employee-form";
    }

    //DELETING NEW Employee
    @GetMapping("/employees/delete")
    public String delete(@RequestParam("employeeIdToDelete")int theId){
        userService.deleteById(theId);
        return "redirect:/employees";
    }




}
