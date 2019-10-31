package com.example.ccimp.ui.presenter;

import android.content.Context;

public interface ILoginPresenter {

    void performLogin(String type, String userEmail, String password, Context context);
}
