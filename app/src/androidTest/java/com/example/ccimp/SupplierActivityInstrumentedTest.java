package com.example.ccimp;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.ccimp.ui.LoginActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class SupplierActivityInstrumentedTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> login = new ActivityScenarioRule<>(LoginActivity.class);

    String email = "supplier@ccimp.com";
    String pwd = "123";

    private void open(){
        onView(withId(R.id.input_email)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.input_password)).perform(typeText(pwd), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHome(){
        open();
        onView(withId(R.id.supplierNavigation)).check(matches(isDisplayed()));
    }

    @Test
    public void testInventory(){
        open();
        onView(withId(R.id.navigation_supplier_inventory)).perform(click());
        onView(withId(R.id.btnadditem)).check(matches(isDisplayed()));
    }

    @Test
    public void testProfile(){
        open();
        onView(withId(R.id.navigation_supplier_profile)).perform(click());
        onView(withId(R.id.user_profile_name)).check(matches(isDisplayed()));
    }
/**
    @Test
    public void testRequestDetail(){
        open();
        onView(withId(R.id.current_requests_listview)).perform(click());
        onView(withId(R.id.requestiems)).check(matches(isDisplayed()));
    }
**/
    @Test
    public void testRequestHistory(){
        open();
        onView(withId(R.id.btnHistory)).perform(click());
        onView(withId(R.id.previous_requests_listview)).check(matches(isDisplayed()));
    }
}
