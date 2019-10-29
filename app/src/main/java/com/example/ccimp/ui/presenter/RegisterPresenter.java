package com.example.ccimp.ui.presenter;

public interface RegisterPresenter {
    void performSignup(String fullName, String email, String address, String phone, String password, String rePassword, String userType);
}
