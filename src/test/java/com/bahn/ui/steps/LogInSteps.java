package com.bahn.ui.steps;

import com.bahn.ui.domain.User;
import com.bahn.ui.pageobjects.AuthorizedUserPage;
import com.bahn.ui.pageobjects.BahnComPage;
import com.bahn.ui.pageobjects.HomePage;
import com.bahn.ui.pageobjects.LogInPage;

public class LogInSteps extends LogInPage {

    private HomePage homePage;
    private LogInPage logInPage;
    private AuthorizedUserPage authorizedUserPage;

    public void openHomePageAndAcceptCookies(String language) {
        homePage = new BahnComPage().openPage()
                .clickButtonAcceptCookies()
                .selectLanguage(language);
        homePage.setHomePageUrl();
    }

    public void openLoginForm() {
        logInPage = homePage.openPage()
                .clickButtonLogin();
    }

    public void fillLoginForm(User user){
       logInPage = logInPage.typeUserName(user.getUsername())
                .typePassword(user.getPassword());
    }
    public void clickLogin(){
        authorizedUserPage = logInPage.clickButtonLogIn();
    }

    public String getUserName(){
        System.out.println("Welcome " + authorizedUserPage.getTitleWelcomeUser());
        System.out.println("UserUp " + authorizedUserPage.getUserLogin());
        return authorizedUserPage.getTitleWelcomeUser();
    }

}
