package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderPage extends BasePage {

    @FindBy(css = ".layout-header-icon__icon")
    private WebElement burgerMenuButton;

    public void openBurgerMenu() {
        waitForElementToBeClickable(burgerMenuButton);
        clickElement(burgerMenuButton);
    }
}
