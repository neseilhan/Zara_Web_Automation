package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    @FindBy(css = "div.shop-cart-item-pricing__current span.money-amount__main")
    private WebElement cartItemPrice;

    @FindBy(css = ".zds-quantity-selector__value")
    private WebElement quantityValue;

    @FindBy(xpath = "(//*[name()='svg'][@class='zds-quantity-selector__icon'])[2]")
    private WebElement increaseQuantityButton;

    @FindBy(css = ".zds-cart-item__actions-remove")
    private WebElement removeItemButton;

    @FindBy(css = ".zds-cart__empty")
    private WebElement emptyCartMessage;

    @FindBy(xpath = "//span[contains(text(),'SEPETİNİZ BOŞ')]")
    private WebElement emptyBasketText;


    public String getCartPrice() {
        wait.until(ExpectedConditions.visibilityOf(cartItemPrice));
        return cartItemPrice.getText().trim();
    }

    public void increaseQuantity() {
        WebElement plus = driver.findElement(
                By.cssSelector("div[aria-label*='Bir birim daha ekle'] svg")
        );

        clickElement(plus);
        sleep(800); // animasyon gecikmesi
    }

    public int getQuantity() {

        for (int i = 0; i < 10; i++) {
            try {
                WebElement qtyInput = driver.findElement(
                        By.cssSelector("div.zds-quantity-selector input")
                );

                String val = qtyInput.getAttribute("value").trim();

                if (!val.isEmpty()) {
                    return Integer.parseInt(val);
                }
            } catch (Exception ignored) {}

            sleep(500);
        }
        throw new RuntimeException("Quantity okunamadı!");
    }

    public void removeItem() {

        WebElement removeIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".shop-cart-item-actions__remove-item-icon")
        ));

        scrollIntoView(removeIcon);
        slowMode();

        clickElement(removeIcon);
        slowMode();
    }

    public boolean isBasketEmpty() {
        try {
            waitForElementToBeVisible(emptyBasketText);
            return emptyBasketText.getText().contains("SEPETİNİZ BOŞ");
        } catch (Exception e) {
            return false;
        }
    }

    public void waitUntilCartIsEmpty() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".zds-cart__empty")
            ));
        } catch (Exception e) {
            throw new RuntimeException("Sepet boş görünmedi!");
        }
    }


}
