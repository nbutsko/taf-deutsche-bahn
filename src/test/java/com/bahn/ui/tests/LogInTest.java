package com.bahn.ui.tests;

import com.bahn.ui.domain.User;
import com.bahn.ui.steps.LogInSteps;
import org.testng.annotations.Test;

public class LogInTest extends AbstractTest {

    @Test(enabled = false)
    public void testLogIn() throws InterruptedException {
        LogInSteps logInSteps = new LogInSteps();
        logInSteps.openHomePageAndAcceptCookies("English");
        logInSteps.openLoginForm();
        logInSteps.fillLoginForm(new User("n.butsko92@gmail.com", "Mi123456"));
        Thread.sleep(5000);
        logInSteps.clickLogin();
        Thread.sleep(15000);
        logInSteps.getUserName();
    }

}
