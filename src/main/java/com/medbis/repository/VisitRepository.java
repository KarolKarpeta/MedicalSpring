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
/*
    @Query("SELECT count(r) as number FROM Visit visits WHERE Visit.visitDate  = '2300-02-01'")*/


    int countVisitsByVisitDateBetween(LocalDate startDate, LocalDate endDate);

    List<Visit> findByEmployeeId(int id);

    List<Visit> findByVisitDateAfterAndVisitDateBefore(LocalDate startDate, LocalDate endDate);


/*
   default int countVisitByVisitDateMonthIsLike(String date){
        System.out.println("jestem przy nativequery");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNativeQuery("SELECT count(*) as number FROM visits WHERE extract(month FROM date) = 12;");
        return query.getFirstResult();
    }
*/

}
