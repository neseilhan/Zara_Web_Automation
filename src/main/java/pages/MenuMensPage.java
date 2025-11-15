package pages;

import base.BasePage;
import org.openqa.selenium.By;

public class MenuMensPage extends BasePage {

    private final By burgerMenuButton = By.cssSelector(".layout-header-icon__icon");
    private final By menCategoryButton = By.xpath("//span[@class='layout-categories-category-wrapper layout-categories-category-level-1__content']//span[@class='layout-categories-category-name'][normalize-space()='ERKEK']");
    private final By viewAllButton = By.xpath("//span[normalize-space()='TÜMÜNÜ GÖR']");


    public void navigateToMenViewAll() {
        waitForPageToLoad();

        handleRegionPopupIfVisible();
        acceptCookiesIfVisible();

        safeClick(burgerMenuButton);
        safeClick(menCategoryButton);
        safeClick(viewAllButton);
    }
}
