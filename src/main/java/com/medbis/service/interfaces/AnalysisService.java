package com.medbis.service.interfaces;


import com.medbis.entity.Visit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnalysisService {


    int countVisitsMonthly(int month);

    List<Visit> findByVisitDateAfterAndVisitDateBefore(int month);



}
