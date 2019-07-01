package com.medbis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "visits", schema = "public")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private int visitId;

    @NotNull(message = "is required")
    @Column(name = "employee_id")
    private int visitEmployeeId;

    @NotNull(message = "is required")
    @Column(name = "patient_id")
    private int visitPatientId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "is required")
    @Column(name = "date")
    private LocalDate visitDate;

    @Column(name = "status")
    private  Boolean visitStatus;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", insertable = false, updatable = false)
    private Patient patient;


}
