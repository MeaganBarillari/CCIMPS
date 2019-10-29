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
    public void performLogin(String type, String userEmail, String password) {


        // might do more validation
//        if (TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(password)){
//
//            mLoginView.loginValidations();
//        }
//        else{

//            if (result == "login success"){
//                mLoginView.loginSuccess();
//            }
//            else{
//                mLoginView.loginError();
//            }
// }
    }
}
