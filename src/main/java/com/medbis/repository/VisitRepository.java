package com.medbis.repository;

import com.medbis.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer> {

    @Query("select v, p from Visit v left join v.patient p order by v.visitDate") //JPQL
    List<Visit> findAllVisits();

    List<Visit> findByVisitStatusIsFalse();

    List<Visit> findByVisitStatusIsTrue();
//    @Query("select p from Planet p where p.planetName  = ?1") //JPQL
//    Optional<Visit> findVisitsByEmployee_Id2(String employeeId);
}
