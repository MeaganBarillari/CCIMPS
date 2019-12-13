package com.example.ccimp;

import android.content.Context;

import com.example.ccimp.ui.LoginActivity;
import com.example.ccimp.ui.model.backgroundWorker;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LoginUnitTest {
    /**
    @Test
    public void login_Success() {
        Context context = new LoginActivity();
        String email = "customer@ccimp.com";
        String password = "123";
        backgroundWorker bgWorker = new backgroundWorker(context);

        String result = bgWorker.doInBackground("login", email, password, "Customer");

        assertEquals("login success", result);
    }
**/
    @Test
    public void incorrect_Email(){
        Context context = new LoginActivity();
        String email = "cus@ccimp.com";
        String password = "123";
        backgroundWorker bgWorker = new backgroundWorker(context);

        String result = bgWorker.doInBackground("login", email, password, "Customer");

        assertEquals("login fail", result);

    }

    @Test
    public void incorrect_Password(){
        Context context = new LoginActivity();
        String email = "customer@ccimp.com";
        String password = "1234";
        backgroundWorker bgWorker = new backgroundWorker(context);

        String result = bgWorker.doInBackground("login", email, password, "Customer");

        assertEquals("login fail", result);
    }
/**
    @Test
    public void register_Success(){
        Context context = new RegisterActivity();
        String email = "test1@ccimp.com";
        String password = "1234";
        backgroundWorker bgWorker = new backgroundWorker(context);

        String result = bgWorker.doInBackground("register", "testtest", email, "test", "123", password, "Customer");

        assertEquals("Register success", result);
    }
 **/

}
