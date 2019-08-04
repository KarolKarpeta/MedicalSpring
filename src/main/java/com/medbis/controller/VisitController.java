package com.medbis.controller;

import com.medbis.entity.Employee;
import com.medbis.entity.Patient;
import com.medbis.entity.Treatment;
import com.medbis.entity.Visit;
import com.medbis.mail.MailService;
import com.medbis.pdf.PdfGenerator;
import com.medbis.service.interfaces.CategoryService;
import com.medbis.service.interfaces.TreatmentService;
import com.medbis.service.interfaces.UserService;
import com.medbis.service.interfaces.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class VisitController {

    private VisitService visitService;
    private UserService userService;
    private CategoryService categoryService;
    private TreatmentService treatmentService;
    private MailService mailService;
    private UserService employeeService;


    @Autowired
    public VisitController(VisitService visitService,
                           @Qualifier("PatientServiceImpl") UserService userService,
                           CategoryService categoryService,
                           TreatmentService treatmentService,
                           MailService mailService,
                           @Qualifier("EmployeeServiceImpl") UserService employeeService) {
        this.visitService = visitService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.treatmentService = treatmentService;
        this.mailService = mailService;
        this.employeeService = employeeService;
    }

    @GetMapping("visits/delete")
    public String deleteVisit(@RequestParam("visitId") int visitId){
        this.visitService.deleteById(visitId);
        return "redirect:/visits";
    }





    //Show form for ADD NEW VISIT
    @GetMapping("/visits/showFormForAddVisit")
    public String showFormForAddVisit(@RequestParam("patientId")int thePatientId, Model theModel){
        Patient thePatient = (Patient) userService.findById(thePatientId);
        Visit newVisit = new Visit();

        newVisit.setPatient(thePatient);
        theModel.addAttribute("visit", newVisit);
        theModel.addAttribute("patientId", thePatientId);

        theModel.addAttribute("categories", categoryService.findAll());
        theModel.addAttribute("allTreatments", treatmentService.findAll());
         return "visits/visit-form";
    }

    //ADDING NEW VISITS
    @PostMapping("/visits/{action}/addNewNewVisit")
    public String addNewMedicine(@PathVariable("action") String action, Model theModel, @Valid @ModelAttribute("visit") Visit theVisit, BindingResult bindingResult) {
        Patient thePatient = (Patient) userService.findById(theVisit.getVisitPatientId());
        theVisit.setPatient(thePatient);
        if (bindingResult.hasErrors()) {
            theVisit.setPatient(thePatient);
            theModel.addAttribute("patientId", theVisit.getVisitPatientId());
            theModel.addAttribute("allTreatments", treatmentService.findAll());
            return "visits/visit-form";
        }
        int initialAmountOfPlannedVisit = visitService.findPlannedVisits().size();
        visitService.save(theVisit);
        if (visitService.checkIfNewVisitAdded(initialAmountOfPlannedVisit)) {
            sendMail(theVisit);
        }
        if(action.equals("hold")){
            theVisit.setVisitStatus(true);
            visitService.save(theVisit);
        }
        return "redirect:/visits";
    }


    @GetMapping("visits")
    public String showSplittedList(@RequestParam(value = "status", required = false, defaultValue = "all") String isVisitDone, Model model){
        if(isVisitDone.equals("all") ){
            model.addAttribute("visitsList", visitService.findAll());
        }
        else if(Boolean.valueOf(isVisitDone)){
            model.addAttribute("visitsList", visitService.findAccomplishedVisits());
        }
        else if(!Boolean.valueOf(isVisitDone)){
            model.addAttribute("visitsList", visitService.findPlannedVisits());
        }
        return "visits/visit-list";
    }

    @GetMapping ("/visits/showFormForEditVisit")
    public String showFormForEditMedicine(@RequestParam("visitIdToEdit")int theId, @RequestParam("action") String action ,Model theModel) {

        Visit visitToEdit = visitService.findById(theId);

        theModel.addAttribute("visit", visitToEdit);
        theModel.addAttribute("patientId", visitToEdit.getVisitPatientId() );
        theModel.addAttribute("allTreatments", treatmentService.findAll());

        String holdAction = "hold";

        if(holdAction.equals(action)) {
            return "visits/visit-hold";
        }
        else {
            return "visits/visit-form";
        }
    }

    @GetMapping("/visits/generate-document")
    public String generatePdfRaport(@RequestParam("visitId") int visitId) {
        Visit reportedVisit = this.visitService.findById(visitId);
        PdfGenerator pdfGenerator = new PdfGenerator(reportedVisit);
        pdfGenerator.createVisitRaport();
        return "redirect:/visits/showFormForEditVisit" + "?visitIdToEdit=" + visitId;
    }


    /* TREATMENTS ***************************************/
    //Add NEW ROW FOR TREATMENTS, look params!
    @PostMapping(value="/visits/{action}/addNewNewVisit", params={"addRow"})
    public String addTreatmentRow(@PathVariable("action") String action,  Model theModel, @ModelAttribute("visit") Visit theVisit) {

        Patient thePatient = (Patient) userService.findById(theVisit.getVisitPatientId());
        theModel.addAttribute("patientId", theVisit.getVisitPatientId() );
        theVisit.setPatient(thePatient);
        theVisit.getServices().add(new Treatment()); //.getRows().add(new Row());
        theModel.addAttribute("allTreatments", treatmentService.findAll());
        return "visits/visit-form";
    }


    //DELETE ONE ROW OF TREATMENTS, look params!
    @PostMapping(value="/visits/addNewNewVisit", params={"removeRow"})
    public String delTreatmentRow(Model theModel, @ModelAttribute("visit") Visit theVisit, final HttpServletRequest req) {
        theModel.addAttribute("patientId", theVisit.getVisitPatientId() );
        Patient thePatient = (Patient) userService.findById(theVisit.getVisitPatientId());
        theVisit.setPatient(thePatient);
        theModel.addAttribute("allTreatments", treatmentService.findAll());
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        theVisit.getServices().remove(rowId.intValue());
        return "visits/visit-form";
    }


    private void sendMail(Visit theVisit){
        Employee employee = (Employee) employeeService.findById(theVisit.getVisitEmployeeId());
        JavaMailSenderImpl mailSender = mailService.createMailSender();
        String mail = theVisit.getPatient().getMail();
        SimpleMailMessage mailMessage = this.mailService.createMailMessage(mail, theVisit, employee);
        mailSender.send(mailMessage);
    }


}
