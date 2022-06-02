package com.bahn.ui.pageobjects;

import com.bahn.logger.UtilLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthorizedUserPage extends AbstractPage{

    @FindBy(tagName = "h2")
    private WebElement titleWelcomeUser;

    @FindBy(css = "div#userlogin")
    private WebElement userLogin;

    public String getTitleWelcomeUser(){
        String title = titleWelcomeUser.getText();
        UtilLogger.logger.info(title);
        return title;
    }

    public String getUserLogin(){
        String login = userLogin.getText();
        UtilLogger.logger.info(login);
        return login;
    }
}
