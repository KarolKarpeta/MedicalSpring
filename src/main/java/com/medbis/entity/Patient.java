package com.medbis.entity;

import javax.persistence.*;

@Entity
@Table(name = "patients", schema = "public")
public class Patient extends User {

    @Id
    @GeneratedValue( generator = "patients_patient_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "patients_patient_id_seq",
            allocationSize = 10
    )
    @Column(name = "patient_id")
    private int patientId;

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                '}';
    }
}

