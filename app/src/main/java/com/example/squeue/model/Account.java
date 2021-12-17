package com.example.squeue.model;

import java.io.Serializable;

public class Account implements Serializable {
    private String id;
    private String password;
    private String name;
    private String email;
    private String role;
    private String phone;


    public Account() {

    }

    public Account(String id, String password, String name, String email, String role, String phone) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}