package com.KPDev.pos.view.tm;

import javafx.scene.control.*;


public class CustomerTm {
    private int id;
    private String name;
    private String email;
    private String contact;
    private Double salary;
    private Button button;

    public CustomerTm() {
    }

    public CustomerTm(int id, String name, String email, String contact, Double salary, Button button) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.salary = salary;
        this.button = button;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
