package com.example.ccimp.ui.presenter;

import android.content.Context;

import com.example.ccimp.ui.data.backgroundWorker;
import com.example.ccimp.ui.presenter.ILoginPresenter;
import com.example.ccimp.ui.activities.views.LoginView;

public class LoginPresenter implements ILoginPresenter {

    private LoginView mLoginView;

    public LoginPresenter(LoginView loginView){
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
