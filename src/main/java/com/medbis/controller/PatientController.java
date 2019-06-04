package com.medbis.controller;

import com.medbis.entity.Patient;
import com.medbis.service.interfaces.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping("/patients")
    public String findAll(Model theModel){
        theModel.addAttribute("patientList", patientService.findAll());
        return "patient/patient-list";
    }

    //ADDING NEW PATIENT
    @GetMapping("/patients/showFormForAddPatient")
    public String showFormForAddPatient(Model theModel){
        Patient newPatient = new Patient();
        theModel.addAttribute("patient", newPatient);
        return "patient/patient-form";
    }


    @PostMapping("/patients/addNewPatient")
    public String addNewPatient(@Valid @ModelAttribute("patient")
                                             Patient thePatient,
                                            BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println("Binding: " + bindingResult);
            return "patient/patient-form";
        }else{
//            theMedicine.setMedicineId(0);
            patientService.save(thePatient);
            return "redirect:/patients";
        }
    }

    //EDITING NEW PATIENT
    @GetMapping ("/patients/showFormForEditPatient")
    public String showFormForEditMedicine(@RequestParam("patientIdToEdit")int theId,
                                          Model theModel){
        Patient newPatient = patientService.findById(theId);
        theModel.addAttribute("patient", newPatient);
        return "patient/patient-form";
    }

    //DELETING NEW PATIENT
    @GetMapping("/patients/delete")
    public String delete(@RequestParam("patientIdToDelete")int theId){
        patientService.deleteById(theId);
        return "redirect:/patients";
    }





}
