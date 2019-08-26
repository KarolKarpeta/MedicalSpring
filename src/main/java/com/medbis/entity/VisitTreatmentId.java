package com.medbis.entity;


import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class VisitTreatmentId implements Serializable {
    private Visit visit;
    private Treatment treatment;

    @ManyToOne(cascade = CascadeType.ALL)
    public Visit getVisit(){
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }
}
