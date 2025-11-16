package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[normalize-space()='Oturum aç']")
    private WebElement loginButton;


    public void login(String email, String password) {

        waitForPageToLoad();

        waitForElementToBeVisible(emailInput);
        emailInput.clear();
        emailInput.sendKeys(email);

        waitForElementToBeVisible(passwordInput);
        passwordInput.clear();
        passwordInput.sendKeys(password);

        waitForElementToBeClickable(loginButton);
        clickElement(loginButton);

        slowMode();
    }
}
