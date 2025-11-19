package pages;

import base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class SearchPage extends BasePage {

    @FindBy(css = ".layout-header-action-search__content")
    private WebElement searchButton;

    @FindBy(css = "#search-home-form-combo-input")
    private WebElement searchInput;

    @FindBy(css = "a.product-grid-product__link")
    private List<WebElement> productCardList;


    public void openSearch() {
        safeClick(searchButton);
        slowMode();
    }

    public void typeKeyword(String keyword) {
        searchInput.clear();
        searchInput.sendKeys(keyword);
        slowMode();
    }

    public void clearSearchInput() {
        searchInput.sendKeys(Keys.CONTROL, "a");
        searchInput.sendKeys(Keys.DELETE);
        slowMode();
    }

    public void submitSearch() {
        searchInput.sendKeys(Keys.ENTER);
        slowMode();
    }

    // Arama sonuçlarından random ürün seç
    public void selectRandomProduct() {
        if (productCardList.isEmpty()) {
            throw new RuntimeException("Ürün bulunamadı!");
        }

        Random rnd = new Random();
        WebElement randomProduct = productCardList.get(rnd.nextInt(productCardList.size()));
        safeClick(randomProduct);
        slowMode();
    }
}
