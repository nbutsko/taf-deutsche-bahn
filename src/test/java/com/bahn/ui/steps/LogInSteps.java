package com.bahn.ui.steps;

import com.bahn.ui.domain.User;
import com.bahn.ui.pageobjects.AuthorizedUserPage;
import com.bahn.ui.pageobjects.BahnComPage;
import com.bahn.ui.pageobjects.HomePage;
import com.bahn.ui.pageobjects.LogInPage;
import io.qameta.allure.Step;

public class LogInSteps extends LogInPage {

    private LogInPage logInPage;
    private AuthorizedUserPage authorizedUserPage;

    public final static String MESSAGE_SERVICE_UNAVAILABLE = "503 Service Unavailable";

    @Step("Open home page in {0}, accept all cookies and open login form")
    public void openLoginForm(String pageLanguage) {
        HomePage homePage = new BahnComPage().openPage()
                .clickButtonAcceptCookies()
                .selectLanguage(pageLanguage);
        homePage.setHomePageUrl();
        logInPage = homePage.openPage()
                .clickButtonLogin();
    }

    @Step("Fill out and submit login form")
    public void fillAndSubmitLoginForm(User user) {
        authorizedUserPage = logInPage.typeUserName(user.getUsername())
                .typePassword(user.getPassword())
                .clickButtonLogIn();
    }

    @Step("Manually enter the captcha")
    public void waitAndManuallyEnterCaptcha() {
        logInPage.waitAndEnterCaptcha();
    }

    @Step("Get error message")
    public String getErrorMessage() {
        return authorizedUserPage.getErrorMessage();
    }
}
