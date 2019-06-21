package com.medbis.entity;

import javax.persistence.*;

@Entity
@Table(name = "patients", schema = "public")
public class Patient extends User {

    @Id
    @GeneratedValue(  strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name = "patients_patient_id_seq") generator = "patients_patient_id_seq",


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = ""
    )


    @Column(name = "patient_id")
    private int patientId;

    @Column(name = "comments")
    private String comments;

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

