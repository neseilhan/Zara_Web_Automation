package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchMensPage extends BasePage {

    private final By searchButton = By.cssSelector(".layout-header-action-search__content");
    private final By searchInput  = By.cssSelector("#search-home-form-combo-input");

    public void openSearch() {
        safeClick(searchButton);
    }

    public void typeKeyword(String keyword) {
        type(searchInput, keyword);
    }

    public void clearSearchInput() {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInput)
        );

        element.sendKeys(Keys.CONTROL, "a");

        element.sendKeys(Keys.DELETE);

        slowMode(); // Görsel takip için
    }


    public void submitSearch() {
        pressEnter(searchInput);
    }
}
