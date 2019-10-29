package com.example.ccimp.ui.model;

import android.content.Context;

import com.example.ccimp.ui.LoginActivity;
import com.example.ccimp.ui.presenter.LoginPresenter;
import com.example.ccimp.ui.view.LoginView;

public class LoginModel implements LoginPresenter {

    LoginView mLoginView;

    public LoginModel(LoginView loginView){
        this.mLoginView = loginView;
    }
    @Override
    public void performLogin(String type, String userEmail, String password, Context context) {
        backgroundWorker bgworker = new backgroundWorker(context);
        bgworker.execute(type, userEmail, password);


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
