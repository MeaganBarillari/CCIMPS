package com.example.ccimp.ui.interfaces;

import android.content.Context;

public interface RegisterInterface {

    interface RegisterPresenter {
        void performSignup(String type, String username, String email, String address, String phone, String password, String rePassword, String userType, Context context);
    }

    interface RegisterView {
        void signupValidations();
        void signupSuccess();
        void pwdUnmatch();
    }
}
