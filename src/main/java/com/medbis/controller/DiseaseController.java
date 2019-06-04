package com.medbis.controller;


import com.medbis.entity.Disease;
import com.medbis.service.interfaces.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DiseaseController {

    private DiseaseService diseaseService;

    @Autowired
    DiseaseController(){
        this.diseaseService = diseaseService;
    }

    @PostMapping("/diseases")
    public List<Disease> getAllDiseases(){
        return diseaseService.findAll();
    }


}