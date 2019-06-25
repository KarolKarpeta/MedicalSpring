package com.medbis.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "patients", schema = "public")
public class Patient extends User {
    public Patient() {
    }

    public Patient(int patientId, String comments) {
        this.patientId = patientId;
        this.comments = comments;
    }

    @Id
    @GeneratedValue(  strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name = "patients_patient_id_seq") generator = "patients_patient_id_seq",
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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "patient_medicines",
            joinColumns = { @JoinColumn(name = "patient_id") },
            inverseJoinColumns = { @JoinColumn(name = "medicine_id") }
    )
    private List<Medicine> patientMedicines = new ArrayList<>();

    public List<Medicine> getPatientMedicines() {
        return patientMedicines;
    }

    public void setPatientMedicines(List<Medicine> patientMedicines) {
        this.patientMedicines = patientMedicines;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", comments='" + comments + '\'' +
                ", patientMedicines=" + patientMedicines +
                '}';
    }
}

