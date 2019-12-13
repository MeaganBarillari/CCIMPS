package com.example.ccimp;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.ccimp.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MainActivityInstrumentedTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mainTest = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainLogin(){
        onView(withId(R.id.btn_activity_login)).perform(click());
        onView(withId(R.id.btn_login)).check(matches(isDisplayed()));
    }

    @Test
    public void mainRegister(){
        onView(withId(R.id.btn_activity_register)).perform(click());
        onView(withId(R.id.input_name)).check(matches(isDisplayed()));
    }

    @Test
    public void enterEmailIncorrect(){
        onView(withId(R.id.input_email)).perform(typeText("wrong"), closeSoftKeyboard());
        onView(withId(R.id.input_password)).perform(typeText("wrong"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.btn_login)).check(matches(isDisplayed()));
    }

}
