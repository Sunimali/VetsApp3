package com.example.user.vetsapp;

import java.io.Serializable;

public class vaccineEntity implements Serializable{
    private String vaccine;
    private String date;
    private String status;
    private int id;

    public vaccineEntity(int id, String date, String status, String vaccine) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.vaccine = vaccine;
    }

    public vaccineEntity() {

    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
