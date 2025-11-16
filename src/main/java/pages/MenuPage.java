package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuPage extends BasePage {

    @FindBy(css = ".layout-header-icon__icon")
    private WebElement burgerMenuButton;

    @FindBy(xpath = "//span[@class='layout-categories-category-wrapper layout-categories-category-level-1__content']//span[@class='layout-categories-category-name'][normalize-space()='ERKEK']")
    private WebElement menCategoryButton;

    @FindBy(xpath = "//span[normalize-space()='TÜMÜNÜ GÖR']")
    private WebElement viewAllButton;
    public void waitForMenuToLoad() {
        waitForElementToBeVisible(By.xpath("//span[normalize-space()='ERKEK']"));
    }

    public void navigateToMenViewAll() {

        waitForPageToLoad();
        handleRegionPopupIfVisible();
        acceptCookiesIfVisible();

        safeClick(burgerMenuButton);
        slowMode();

        safeClick(menCategoryButton);
        slowMode();

        safeClick(viewAllButton);
        slowMode();
    }
}
