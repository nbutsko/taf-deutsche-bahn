package com.bahn.ui.tests;

import com.bahn.ui.domain.User;
import com.bahn.ui.steps.LogInSteps;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LogInTest extends AbstractTest {

    @Epic("UI")
    @Feature("Login")
    @Story("Smoke")
    @Test(description = "Smoke - login form", enabled = false)
    public void testLogIn(){
        LogInSteps logInSteps = new LogInSteps();
        logInSteps.openLoginForm("English");

        logInSteps.fillAndSubmitLoginForm(new User("n.butsko92@gmail.com", "Mi123456"));
        logInSteps.waitAndManuallyEnterCaptcha();

        assertEquals(logInSteps.getErrorMessage(), LogInSteps.MESSAGE_SERVICE_UNAVAILABLE);
    }
}
