package com.example.ccimp.ui.presenter;

import android.content.Context;

import com.example.ccimp.ui.interfaces.RegisterInterface;
import com.example.ccimp.ui.model.backgroundWorker;

public class RegisterPresenter implements RegisterInterface.RegisterPresenter {
    RegisterInterface.RegisterView mRegisterView;

    public RegisterPresenter(RegisterInterface.RegisterView registerView) {
        this.mRegisterView = registerView;
    }

    @Override
    public void performSignup(String type, String username, String email, String address, String phone, String password, String rePassword, String userType, Context context){
        if(!password.equals(rePassword)){
            mRegisterView.pwdUnmatch();
        }
        else {
            backgroundWorker bgWorker = new backgroundWorker(context);
            bgWorker.execute(type, username, email, address, phone, password, userType);

//            if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(address) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password) || TextUtils.isEmpty(rePassword) || TextUtils.isEmpty(userType)){
//                mRegisterView.signupValidations();
//            }
//            else{
//                mRegisterView.signupSuccess();
//            }

        }

    }
}