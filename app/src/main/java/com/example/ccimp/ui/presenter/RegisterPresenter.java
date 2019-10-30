package com.example.ccimp.ui.presenter;

import android.content.Context;

public interface RegisterPresenter {
    void performSignup(String type, String username, String email, String address, String phone, String password, String rePassword, String userType, Context context);
}
