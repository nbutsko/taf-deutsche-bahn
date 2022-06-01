package com.bahn.ui.pageobjects;

import com.bahn.utils.UtilLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends AbstractPage{

    @FindBy(css = "input#username")
    private WebElement inputUsername;

    @FindBy(css = "input#password")
    private WebElement inputPassword;

    @FindBy(css = "button#kc-login")
    private WebElement buttonLogIn;

    public LogInPage typeUserName(String username){
        inputUsername.clear();
        inputUsername.sendKeys(username);
        UtilLogger.logger.info("Type username " + username);
        return this;
    }

    public LogInPage typePassword(String password){
        inputPassword.clear();
        inputPassword.sendKeys(password);
        UtilLogger.logger.info("Type password " + password);
        return this;
    }

    public AuthorizedUserPage clickButtonLogIn(){
        buttonLogIn.click();
        UtilLogger.logger.info("Click buttonLogIn");
        return new AuthorizedUserPage();
    }
}
