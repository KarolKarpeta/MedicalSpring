package com.medbis.controller;

import com.medbis.entity.Patient;
import com.medbis.factory.UserFactory;
import com.medbis.service.interfaces.UserService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class PatientController {


    private UserService userService;
    private UserFactory userFactory;

    @Autowired
    public PatientController(@Qualifier(value = "PatientServiceImpl") UserService userService, UserFactory userFactory) {
        this.userService = userService;
        this.userFactory = userFactory;
    }


    @GetMapping("/patients")
    public String findAll(Model theModel){
        theModel.addAttribute("patientList", userService.findAll());
        return "users/patient-list";
    }

    //ADDING NEW PATIENT
    @GetMapping("/patients/showFormForAddPatient")
    public String showFormForAddPatient(Model theModel){
        theModel.addAttribute("patient", userFactory.getNewUser("patient"));
        return "users/patient-form";
    }


    @PostMapping("/patients/addNewPatient")
    public String addNewPatient(
            @Valid @ModelAttribute("patient") Patient thePatient,
            BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "users/patient-form";
        }else{
            userService.save(thePatient);
            return "redirect:/patients";
        }
    }

    //EDITING NEW PATIENT
    @GetMapping ("/patients/showFormForEditPatient")
    public String showFormForEditMedicine(@RequestParam("patientIdToEdit")int theId,
                                          Model theModel){
        Patient newPatient = (Patient) userService.findById(theId);
        theModel.addAttribute("patient", newPatient);
        return "users/patient-form";
    }

    //DELETING NEW PATIENT
    @GetMapping("/patients/delete")
    public String delete(@RequestParam("patientIdToDelete")int theId){
        userService.deleteById(theId);
        return "redirect:/patients";
    }





}
