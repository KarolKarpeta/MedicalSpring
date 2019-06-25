package com.medbis.controller;

import com.medbis.entity.Disease;
import com.medbis.entity.Medicine;
import com.medbis.entity.Patient;
import com.medbis.factory.UserFactory;
import com.medbis.service.interfaces.DiseaseService;
import com.medbis.service.interfaces.MedicineService;
import com.medbis.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class PatientController {

    private MedicineService medicineService;
    private DiseaseService diseaseService;
    private UserService userService;
    private UserFactory userFactory;

    @Autowired
    public PatientController(@Qualifier(value = "PatientServiceImpl") UserService userService, UserFactory userFactory, DiseaseService diseaseService, MedicineService medicineService) {
        this.userService = userService;
        this.userFactory = userFactory;
        this.diseaseService = diseaseService;
        this.medicineService = medicineService;
    }


    @GetMapping("/patients")
    public String findAll(Model theModel){
        theModel.addAttribute("patientList", userService.findAll());
        return "users/patient-list";
    }

    //ADDING NEW PATIENT
    @GetMapping("/patients/showFormForAddPatient")
    public String showFormForAddPatient(Model theModel){
//        theModel.addAttribute("diseases", diseaseService.findAll());
        Patient newPatient = (Patient) userFactory.getNewUser("patient");
//        newPatient.getPatientMedicines().add(new Medicine());
//        newPatient.getPatientMedicines().add(new Medicine());
//        newPatient.getPatientMedicines().add(new Medicine());
        theModel.addAttribute("patient",newPatient);

        theModel.addAttribute("allMedicines", medicineService.findAll());
        return "users/patient-form";
    }

    @PostMapping(value="/patients/addNewPatient", params={"addRow"})
    public String addRow(Model theModel, @ModelAttribute("patient") Patient thePatient) {
        thePatient.getPatientMedicines().add(new Medicine()); //.getRows().add(new Row());
        theModel.addAttribute("allMedicines", medicineService.findAll());
        System.out.println("Add new row -----------------");
        return "users/patient-form";
    }


    @PostMapping("/patients/addNewPatient")
    public String addNewPatient(Model theModel,
            @Valid @ModelAttribute("patient") Patient thePatient,
            BindingResult bindingResult){
        System.out.println("MOdel patient: " + thePatient.getPatientMedicines());

        if (bindingResult.hasErrors()){
            theModel.addAttribute("allMedicines", medicineService.findAll());
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

//        Set<Medicine> medicineSet = new HashSet<>(Arrays.asList(new Medicine(), new Medicine()));
//        newPatient.setPatientMedicines(medicineSet);
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

    @GetMapping ("/patients/showPatientDetails")
    public String showPatientDetails(@RequestParam("patientIdDetails")int theId, Model theModel){
        Patient newPatient = (Patient) userService.findById(theId);
        System.out.println("Patient details: " + newPatient.toString());
        theModel.addAttribute("patient", newPatient);
        return "users/patient-details";
    }





}
