package com.medbis.repository;

import com.medbis.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer> {


    List<Visit> findAll();

    List<Visit> findByVisitStatusIsFalse();

    List<Visit> findByVisitStatusIsTrue();

    int countVisitsByEmployeeId(int id);

    int countVisitsByVisitDateBetween(LocalDate startDate, LocalDate endDate);


    int countVisitsByVisitDateBetweenAndEmployeeId(LocalDate startDate, LocalDate endDate, int employeeId);

    int countVisitsByEmployeeIdAndVisitStatus(int id, boolean visitStatus);



}
