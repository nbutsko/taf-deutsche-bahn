package com.bahn.ui.pageobjects;

import com.bahn.logger.UtilLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogInPage extends AbstractPage {

    @FindBy(css = "input#username")
    private WebElement inputUsername;

    @FindBy(css = "input#password")
    private WebElement inputPassword;

    @FindBy(css = "button#kc-login")
    private WebElement buttonLogIn;

    @FindBy(xpath = "//iframe[@title='Main content of the hCaptcha challenge']")
    private WebElement captchaFrame;

    @FindBy(css = "div.challenge")
    private WebElement captcha;

    public LogInPage typeUserName(String username) {
        inputUsername.clear();
        inputUsername.sendKeys(username);
        UtilLogger.logger.info("Type username " + username);
        return this;
    }

    public LogInPage typePassword(String password) {
        inputPassword.clear();
        inputPassword.sendKeys(password);
        UtilLogger.logger.info("Type password " + password);
        return this;
    }

    public AuthorizedUserPage clickButtonLogIn() {
        buttonLogIn.click();
        UtilLogger.logger.info("Click buttonLogIn");
        return new AuthorizedUserPage();
    }

    public void waitAndEnterCaptcha(){
        driver.switchTo().frame(captchaFrame);
        getWebDriverWait(driver).until(ExpectedConditions.visibilityOf(captcha));
        //here a captcha is manually entered
        getWebDriverWait(driver).until(ExpectedConditions.invisibilityOf(captcha));
        driver.switchTo().defaultContent();
    }
}
