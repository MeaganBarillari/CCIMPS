package com.example.ccimp.ui.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.ccimp.ui.interfaces.RegisterInterface;
import com.example.ccimp.ui.model.backgroundWorker;

public class RegisterPresenter implements RegisterInterface.RegisterPresenter {
    RegisterInterface.RegisterView mRegisterView;

    public RegisterPresenter(RegisterInterface.RegisterView registerView) {
        this.mRegisterView = registerView;
    }

    @Override
    public void performSignup(String type, String username, String email, String address, String phone, String password, String rePassword, String userType, Context context){

        if(!validate(username,email,address,phone,password,rePassword,userType)){
            mRegisterView.signupValidations();
            return;
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

    public boolean validate(String username, String email, String address, String phone, String password, String rePassword, String userType){
        boolean valid = true;

        if(password.isEmpty() || username.isEmpty() || email.isEmpty() ||
            address.isEmpty() || phone.isEmpty() || rePassword.isEmpty() ||
            userType.isEmpty()){
            valid = false;
        }
        return valid;
    }
}