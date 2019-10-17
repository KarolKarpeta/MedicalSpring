package com.medbis.repository;

import com.medbis.entity.Treatment;
import com.medbis.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {

    List<Treatment> findAllByCategoryId(int categoryId);

    @Query(value = "select s.service_id as serviceID,s.services_name as serviceName, c.name_category as categoryName from categories c left join services s on c.category_id = s.category_id", nativeQuery = true)
    List<TreatAndCatDTO> findAllTreatmentsAndCategory();

    // Interface to which result is projected
    public interface TreatAndCatDTO {

        Integer getServiceID();
        String getServiceName();
        String getCategoryName();
    }
}