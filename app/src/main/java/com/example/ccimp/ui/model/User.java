package com.example.ccimp.ui.model;

public class User {

    private int userid;
    private String fullName, email, password, userType, phone, address;

    public User(String fullName, String email, String password, String userType, String phone, String address, int userid) {
        this.email = email;
        this.password = password;
        this.userid = userid;
        this.userType = userType;
        this.phone = phone;
        this.address = address;
        this.fullName = fullName;
    }



}