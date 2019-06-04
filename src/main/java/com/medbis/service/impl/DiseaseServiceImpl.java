package com.medbis.service.impl;

import com.medbis.entity.Disease;
import com.medbis.repository.DiseaseRepository;
import com.medbis.service.interfaces.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class DiseaseServiceImpl implements DiseaseService {

    @Autowired
    public DiseaseServiceImpl() {
        this.diseaseRepository = diseaseRepository;
    }

    private DiseaseRepository diseaseRepository;

    @Override
    public List<Disease> findAll() {
        return diseaseRepository.findAll();
    }

    public Disease findById(int id){
        Optional<Disease> disease =  this.diseaseRepository.findById(id);
        if(!disease.isPresent()){
            throw new RuntimeException("item with" + id + "not found");
        }
        return disease.get();
    }
}
