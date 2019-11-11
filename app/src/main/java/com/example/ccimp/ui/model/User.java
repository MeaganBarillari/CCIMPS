package com.example.ccimp.ui.model;

public class User {

    private int userID;
    private String username;
    private String email;
    private String password;
    private String type;
    private String phone;
    private String address;

    public User(int userID, String username, String email, String password, String type, String phone, String address) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
        this.phone = phone;
        this.address = address;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}