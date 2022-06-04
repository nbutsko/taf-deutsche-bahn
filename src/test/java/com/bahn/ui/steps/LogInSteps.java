package com.bahn.ui.steps;

import com.bahn.ui.domain.User;
import com.bahn.ui.pageobjects.AuthorizedUserPage;
import com.bahn.ui.pageobjects.BahnComPage;
import com.bahn.ui.pageobjects.HomePage;
import com.bahn.ui.pageobjects.LogInPage;

public class LogInSteps extends LogInPage {

    private LogInPage logInPage;
    private AuthorizedUserPage authorizedUserPage;

    public final static String MESSAGE_SERVICE_UNAVAILABLE = "503 Service Unavailable";

    public void openLoginForm(String pageLanguage) {
        HomePage homePage = new BahnComPage().openPage()
                .clickButtonAcceptCookies()
                .selectLanguage(pageLanguage);
        homePage.setHomePageUrl();
        logInPage = homePage.openPage()
                .clickButtonLogin();
    }

    public void fillAndSubmitLoginForm(User user) {
        authorizedUserPage = logInPage.typeUserName(user.getUsername())
                .typePassword(user.getPassword())
                .clickButtonLogIn();
    }

    public void waitAndManuallyEnterCaptcha() {
        logInPage.waitAndEnterCaptcha();
    }

    public String getErrorMessage() {
        return authorizedUserPage.getErrorMessage();
    }
}
