package com.example.ccimp.ui.presenter;

import android.content.Context;

import com.example.ccimp.ui.interfaces.LoginInterface;
import com.example.ccimp.ui.model.backgroundWorker;

public class LoginPresenter implements LoginInterface.LoginPresenter {

    LoginInterface.LoginView mLoginView;

    public LoginPresenter(LoginInterface.LoginView loginView) {
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
