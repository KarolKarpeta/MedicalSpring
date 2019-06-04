package com.medbis.entity;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity()
@Table(name="diseases", schema="public")
public class Disease {

    public int getDiseasesId() {
        return diseaseId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="diseases_id")
    private int diseaseId;
    @Column(name = "diseases_code")
    @Size(min=1, max=6)
    private String code;
    @Column(name="diseases_name")
    @NotEmpty
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
