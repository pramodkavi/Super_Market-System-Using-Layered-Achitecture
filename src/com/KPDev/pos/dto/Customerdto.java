package com.KPDev.pos.dto;

public class Customerdto {
    private String name;
    private String email;
    private String contact;
    private Double salary;

    public Customerdto(String name, String email, String contact, Double salary) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.salary = salary;
    }

    public Customerdto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
