package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement cookiesAcceptButton;

    @FindBy(xpath = "//ul[@class='layout-header-actions']//a[contains(text(),'GİRİŞ YAP')]")
    private WebElement loginButtonOnHeader;

    public void acceptCookies() {
        try {
            waitForElementToBeVisible(cookiesAcceptButton);
            clickElement(cookiesAcceptButton);
        } catch (Exception ignored) {}
    }

    public void goToLoginPage() {
        waitForElementToBeClickable(loginButtonOnHeader);
        clickElement(loginButtonOnHeader);
        slowMode();
    }
}
