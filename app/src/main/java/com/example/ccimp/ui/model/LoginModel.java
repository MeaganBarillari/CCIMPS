package com.example.ccimp.ui.model;

import android.text.TextUtils;

import com.example.ccimp.ui.presenter.LoginPresenter;
import com.example.ccimp.ui.view.LoginView;

public class LoginModel implements LoginPresenter {

    LoginView mLoginView;

    public LoginModel(LoginView loginView){
        this.mLoginView = loginView;
    }
    @Override
    public void performLogin(String userEmail, String password){

        if (TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(password)){
            mLoginView.loginValidations();
        }

        else{
            if (userEmail.equals("abc@wisc.edu") && password.equals("abc")){
                mLoginView.loginSuccess();
            }
            else{
                mLoginView.loginError();
            }
        }
    }
}
