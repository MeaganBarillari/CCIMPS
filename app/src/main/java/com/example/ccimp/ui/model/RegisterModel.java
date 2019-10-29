package com.example.ccimp.ui.model;

import android.text.TextUtils;
import android.widget.Toast;

import com.example.ccimp.ui.presenter.RegisterPresenter;
import com.example.ccimp.ui.view.RegisterView;

public class RegisterModel implements RegisterPresenter {
    RegisterView mRegisterView;

    public RegisterModel(RegisterView registerView){
        this.mRegisterView = registerView;
    }

    @Override
    public void performSignup(String fullName, String email, String address, String phone, String password, String rePassword, String userType){
        if(!password.equals(rePassword)){
            mRegisterView.pwdUnmatch();
        }
        else {
            if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(address) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(rePassword) || TextUtils.isEmpty(userType)){
                mRegisterView.signupValidations();
            }
            else{
                mRegisterView.signupSuccess();
            }

        }

    }
}