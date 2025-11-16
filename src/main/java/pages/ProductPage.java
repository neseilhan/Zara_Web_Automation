package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class ProductPage extends BasePage {
    @FindBy(css = ".product-detail-info__header-name")
    private WebElement productName;

    @FindBy(css = ".money-amount__main")
    private WebElement productPrice;

    @FindBy(css = ".zds-button.product-detail-cart-buttons__button")
    private WebElement addToCartButton;

    @FindBy(css = "[data-qa-qualifier='size-selector-sizes-size-label']")
    private WebElement sizeSelector;

    @FindBy(css = "[data-qa-id='zds-alert-dialog-cancel-button']")
    private WebElement noThanksButton;

    @FindBy(css = "[data-qa-id='layout-header-go-to-cart-items-count']")
    private WebElement basketButton;


    public void waitForProductPageToLoad() {
        wait.until(ExpectedConditions.visibilityOf(productName));
        wait.until(ExpectedConditions.visibilityOf(productPrice));
    }

    public String getProductName() {
        return productName.getText().trim();
    }

    public String getProductPrice() {
        return productPrice.getText().trim();
    }

    public void selectSize() {

        clickElement(addToCartButton);
        slowMode();

        List<WebElement> sizes = wait.until(driver -> {
            List<WebElement> els = driver.findElements(
                    By.cssSelector("[data-qa-qualifier='size-selector-sizes-size-label']")
            );
            return (els != null && !els.isEmpty()) ? els : null;
        });

        if (sizes == null || sizes.isEmpty()) {
            throw new RuntimeException("Inline beden listesi bulunamadı!");
        }

        Random rnd = new Random();
        WebElement randomSize = sizes.get(rnd.nextInt(sizes.size()));

        scrollIntoView(randomSize);
        clickElement(randomSize);
        slowMode();

    }


    public void clickAddToCartButton() {
        clickElement(addToCartButton);
    }


    public void closeRecommendationPopupIfVisible() {
        try {
            waitForElementToBeClickable(noThanksButton);
            clickElement(noThanksButton);
        } catch (Exception ignored) {
            // popup çıkmadı → sorun değil
        }
    }

    public void goToBasketFromHeader() {

        sleep(4000);

        By basketButton = By.xpath("//span[normalize-space()='Sepet']");

        WebElement basket = wait.until(ExpectedConditions.elementToBeClickable(basketButton));

        scrollIntoView(basket);
        safeClick(basket);

        slowMode();
    }

    public void clickBasketButton() {
        clickElement(basketButton);
    }
}