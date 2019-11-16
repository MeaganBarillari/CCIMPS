package com.example.ccimp.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String userID;
    private String username;
    private String email;
    private String password;
    private String type;
    private String phone;
    private String address;

    public User(String userID, String username, String email, String password, String type, String phone, String address) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
        this.phone = phone;
        this.address = address;
    }

    protected User(Parcel in) {
        userID = in.readString();
        username = in.readString();
        email = in.readString();
        password = in.readString();
        type = in.readString();
        phone = in.readString();
        address = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userID);
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(type);
        dest.writeString(phone);
        dest.writeString(address);
    }
}