package com.medbis.service.impl;

import com.medbis.entity.Visit;
import com.medbis.repository.VisitRepository;
import com.medbis.service.interfaces.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitServiceImpl implements VisitService {

    private VisitRepository visitRepository;

    @Autowired
    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public List<Visit> findAll() {
        return visitRepository.findAll();
    }

    @Override
    public Visit findById(int theId) {
        Optional<Visit> visit =  this.visitRepository.findById(theId);
        if(!visit.isPresent()){
            throw new RuntimeException("item with" + theId + "not found");
        }
        return visit.get();
    }

    @Override
    public void save(Visit visit) {
        this.visitRepository.save(visit);
    }

    @Override
    public void deleteById(int theId) {
        this.visitRepository.deleteById(theId);
    }

    @Override
    public List<Visit> findAllVisits() {
        return visitRepository.findAllVisits();
    }

    @Override
    public List<Visit> findPlannedVisits(){ return visitRepository.findByVisitStatusIsFalse();}

    @Override
    public List<Visit> findAccomplishedVisits() {
        return visitRepository.findByVisitStatusIsTrue();
    }


}
