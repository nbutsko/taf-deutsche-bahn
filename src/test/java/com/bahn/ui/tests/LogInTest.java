package com.bahn.ui.tests;

import com.bahn.ui.domain.User;
import com.bahn.ui.steps.LogInSteps;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LogInTest extends AbstractTest {

    @Test(groups = "Smoke", description = "Smoke test login form", enabled = false)
    public void testLogIn(){
        LogInSteps logInSteps = new LogInSteps();
        logInSteps.openLoginForm("English");

        logInSteps.fillAndSubmitLoginForm(new User("n.butsko92@gmail.com", "Mi123456"));
        logInSteps.waitAndManuallyEnterCaptcha();

        assertEquals(logInSteps.getErrorMessage(), LogInSteps.MESSAGE_SERVICE_UNAVAILABLE);
    }
}
