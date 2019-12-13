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

public class BusinessActivityInstrumentedTest {

    //change 8
    @Rule
    public ActivityScenarioRule<LoginActivity> login = new ActivityScenarioRule<>(LoginActivity.class);

    String email = "business@ccimp.com";
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
        onView(withId(R.id.btnHistory)).check(matches(isDisplayed()));
    }

    @Test
    public void testInventory(){
        open();
        onView(withId(R.id.navigation_inventory)).perform(click());
        onView(withId(R.id.requestItem)).check(matches(isDisplayed()));
    }

    @Test
    public void testOrderDetail(){
        open();
        onView(withId(R.id.orderlist)).perform(click());
        onView(withId(R.id.customer_number)).check(matches(isDisplayed()));
    }

    @Test
    public void testOrderHistory(){
        open();
        onView(withId(R.id.btnHistory)).perform(click());
        onView(withId(R.id.pastorders)).check(matches(isDisplayed()));
    }

    @Test
    public void testProfile(){
        open();
        onView(withId(R.id.navigation_business_profile)).perform(click());
        onView(withId(R.id.business_profile_name)).check(matches(isDisplayed()));

    }

    @Test
    public void testRequests(){
        open();
        onView(withId(R.id.navigation_requests)).perform(click());
        onView(withId(R.id.current_request_listview)).check(matches(isDisplayed()));

    }

    @Test
    public void testRequestFromSupplier(){
        open();
        onView(withId(R.id.navigation_inventory)).perform(click());
        onView(withId(R.id.requestItem));
    }
}
