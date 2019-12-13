package com.example.ccimp;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.ccimp.ui.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LoginActivityInstrumentedTest {

    @Rule public ActivityScenarioRule<LoginActivity> loginTest = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void enterEmailCorrect(){
        onView(withId(R.id.input_email)).perform(typeText("business@ccimp.com"), closeSoftKeyboard());
        onView(withId(R.id.input_password)).perform(typeText("123"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.customerNavigation)).check(matches(isDisplayed()));
    }

}
