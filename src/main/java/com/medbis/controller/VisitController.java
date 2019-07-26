package com.medbis.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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


    //SHOW ALL VISITS
    @GetMapping ("/visits")
    public String findAll(Model theModel){
        theModel.addAttribute("visitsList", visitService.findAllVisits());
        return "visits/visit-list";
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
    @PostMapping("/visits/addNewNewVisit")
    public String addNewMedicine(Model theModel, @Valid @ModelAttribute("visit") Visit theVisit, BindingResult bindingResult){
        Patient thePatient = (Patient) userService.findById(theVisit.getVisitPatientId());
        if (bindingResult.hasErrors()){
            theVisit.setPatient(thePatient);
            theModel.addAttribute("patientId", theVisit.getVisitPatientId() );
            theModel.addAttribute("allTreatments", treatmentService.findAll());
            return "visits/visit-form";
        }else{
            visitService.save(theVisit);
            Employee employee = (Employee) employeeService.findById(theVisit.getVisitEmployeeId());
            JavaMailSenderImpl mailSender = mailService.createMailSender();
            SimpleMailMessage mailMessage = this.mailService.createMailMessage(thePatient.getMail(),theVisit, employee);
            mailSender.send(mailMessage);
            return "redirect:/visits";
        }
    }

    @GetMapping ("/visits/showFormForEditVisit")
    public String showFormForEditMedicine(@RequestParam("visitIdToEdit")int theId, Model theModel) throws DocumentException, FileNotFoundException {
        Visit visitToEdit = visitService.findById(theId);
        theModel.addAttribute("visit", visitToEdit);
        theModel.addAttribute("patientId", visitToEdit.getVisitPatientId() );
        theModel.addAttribute("allTreatments", treatmentService.findAll());



        return "visits/visit-form";
    }

    @GetMapping("/visits/generate-document")
    public String generatePdfRaport(@RequestParam("visitId") int visitId) throws DocumentException {
        Document document = new Document();
        Visit raportedVisit = visitService.findById(visitId);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(raportedVisit.getVisitDate() + "-" + raportedVisit.getVisitId()+".pdf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PdfWriter pdfWriter = PdfWriter.getInstance(document, fileOutputStream);
        PdfGenerator pdfGenerator = new PdfGenerator(raportedVisit);

        document.open();
        pdfGenerator.setDocumentContent(document);
        document.close();
        pdfWriter.close();

        return "redirect:/visits/showFormForEditVisit" + "?visitIdToEdit=" + visitId;
    }


    /* TREATMENTS ***************************************/
    //Add NEW ROW FOR TREATMENTS, look params!
    @PostMapping(value="/visits/addNewNewVisit", params={"addRow"})
    public String addTreatmentRow(Model theModel, @ModelAttribute("visit") Visit theVisit) {
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



}
