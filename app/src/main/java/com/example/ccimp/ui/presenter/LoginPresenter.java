package com.example.ccimp.ui.presenter;

import android.content.Context;

import com.example.ccimp.ui.model.backgroundWorker;
import com.example.ccimp.ui.presenter.ILoginPresenter;
import com.example.ccimp.ui.view.LoginView;

public class LoginPresenter implements ILoginPresenter {

    LoginView mLoginView;

    public LoginPresenter(LoginView loginView){
        this.mLoginView = loginView;
    }
    @Override
    public void performLogin(String type, String userEmail, String password,String userType, Context context) {
        backgroundWorker bgworker = new backgroundWorker(context);
        bgworker.execute(type, userEmail, password, userType);


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
