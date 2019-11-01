package com.example.ccimp.ui.presenter;

import android.content.Context;

import com.example.ccimp.ui.model.backgroundWorker;
import com.example.ccimp.ui.presenter.IRegisterPresenter;
import com.example.ccimp.ui.view.RegisterView;

public class RegisterPresenter implements IRegisterPresenter {
    RegisterView mRegisterView;

    public RegisterPresenter(RegisterView registerView){
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