package com.medbis.entity;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity()
@Table(name="diseases", schema="public")
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int diseases_id;
    @Column(name = "diseases_code")
    private String code;
    @Column(name="diseases_name")
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
