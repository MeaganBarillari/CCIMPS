package com.example.ccimp.ui.interfaces;

import android.content.Context;

public interface LoginInterface {

    interface LoginPresenter {
        void performLogin(String type, String userEmail, String password, Context context);
    }

    interface LoginView {
        void loginValidations();
        void loginSuccess();
        void loginError();
    }
}
