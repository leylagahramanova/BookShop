package com.example.BookShop.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CheckoutDto {
    @Id
    private int id;
    private String fname;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String cardname;
    private String cardnumber;
    private String expmonth;
    private String expyear;
    private String cvv;

    public CheckoutDto() {
    }
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getExpmonth() {
        return expmonth;
    }

    public void setExpmonth(String expmonth) {
        this.expmonth = expmonth;
    }

    public String getExpyear() {
        return expyear;
    }

    public void setExpyear(String expyear) {
        this.expyear = expyear;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
