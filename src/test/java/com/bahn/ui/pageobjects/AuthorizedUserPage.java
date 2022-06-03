package com.bahn.ui.pageobjects;

import com.bahn.logger.UtilLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthorizedUserPage extends AbstractPage{

    @FindBy(tagName = "h1")
    private WebElement errorMessage;

    @FindBy(css = "div#userlogin")
    private WebElement userLogin;

    public String getErrorMessage(){
        String errorMessageText = errorMessage.getText();
        UtilLogger.logger.info(errorMessageText);
        return errorMessageText;
    }

    public String getUserLogin(){
        String login = userLogin.getText();
        UtilLogger.logger.info(login);
        return login;
    }
}
