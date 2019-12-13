package com.example.ccimp;

import com.example.ccimp.ui.LoginActivity;
import com.example.ccimp.ui.RegisterActivity;
import com.example.ccimp.ui.interfaces.LoginInterface;
import com.example.ccimp.ui.interfaces.RegisterInterface;
import com.example.ccimp.ui.presenter.LoginPresenter;
import com.example.ccimp.ui.presenter.RegisterPresenter;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class LoginPresenterUnitTest {

    @Test
    public void testLogin(){
        LoginInterface.LoginView loginView = new LoginActivity();
        LoginInterface.LoginPresenter loginPresenter = new LoginPresenter(loginView);

        assertNotNull(loginView);
        assertNotNull(loginPresenter);
    }

    @Test
    public void testRegister(){
        RegisterInterface.RegisterView registerView = new RegisterActivity();
        RegisterInterface.RegisterPresenter registerPresenter = new RegisterPresenter(registerView);

        assertNotNull(registerView);
        assertNotNull(registerPresenter);
    }

}
