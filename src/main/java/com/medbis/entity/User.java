package com.medbis.entity;

import javax.persistence.Column;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.*;
import java.time.LocalDate;

@MappedSuperclass
public class User {

    @NotNull(message = "is required")
    @Pattern(regexp = "^[0-9]{11}", message = "only 11 digits")
    @Column(name = "pesel")
    private String pesel;

    @NotNull(message = "is required")
    @Size(min = 3, message = "to short")
    @Column(name = "surname")
    private String surname;

    @NotNull(message = "is required")
    @Size(min = 3, message = "to short")
    @Column(name = "name")
    private String name;

    @NotNull(message = "is required")
    @Column(name = "birthday")
    private LocalDate birthday;

    @NotNull(message = "is required")
    @Size(min = 3, message = "to short")
    @Column(name = "sex")
    private String sex;

    @NotNull(message = "is required")
    @Size(min = 3, message = "to short")
    @Column(name = "community")
    private String community;

    @NotNull(message = "is required")
    @Pattern(regexp = "^[0-9]{5}", message = "only 5 digits")
    @Column(name = "zip_code")
    private String zipCode;

    @NotNull(message = "is required")
    @Size(min = 3, message = "to short")
    @Column(name = "city")
    private String city;

    @NotNull(message = "is required")
    @Size(min = 3, message = "to short")
    @Column(name = "street")
    private String street;

    @NotNull(message = "is required")
    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "apartment_number")
    private String apartmentNumber;

    @Column(name = "home_phone_number")
    private String homePhoneNumber;

    @Column(name = "work_phone_number")
    private String workPhoneNumber;

    @Column(name = "mobile_phone_number")
    private String mobilePhoneNumber;


    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public String getWorkPhoneNumber() {
        return workPhoneNumber;
    }

    public void setWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

   /* public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }*/

    @Override
    public String toString() {
        return "User{" +
                "pesel=" + pesel +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", community='" + community + '\'' +
                ", zipCode=" + zipCode +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", apartmentNumber=" + apartmentNumber +
                ", homePhoneNumber=" + homePhoneNumber +
                ", workPhoneNumber=" + workPhoneNumber +
                ", mobilePhoneNumber=" + mobilePhoneNumber +
                '}';
    }
}