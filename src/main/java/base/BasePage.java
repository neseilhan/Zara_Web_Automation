package base;

import config.SeleniumConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private final By acceptCookiesButton = By.cssSelector("button#onetrust-accept-btn-handler");
    private final By regionContinueButton = By.cssSelector("button[data-qa='continue-button']");


    public BasePage() {
        this.driver = SeleniumConfig.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        org.openqa.selenium.support.PageFactory.initElements(driver, this);
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        slowMode();
    }

    protected void pressEnter(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(Keys.ENTER);
    }

    public void acceptCookiesIfVisible() {
        try {
            WebElement button = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.presenceOfElementLocated(acceptCookiesButton));

            if (button.isDisplayed()) {
                button.click();
            }
        } catch (Exception ignore) {
            // Cookie popup not present → safe to continue
        }
    }

    public void waitForPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );
    }
    protected void safeClick(WebElement element) {
        for (int i = 0; i < 3; i++) {
            try {
                scrollIntoView(element);
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                return;
            } catch (StaleElementReferenceException stale) {
                sleep(300);
            } catch (Exception e) {
                jsClick(element);
                return;
            }
        }
        throw new RuntimeException("Failed to click WebElement: " + element);
    }

    protected void jsClick(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        } catch (Exception ignored) {}
    }


    protected void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ignored) {}
    }
    public void handleRegionPopupIfVisible() {
        try {
            WebElement continueBtn = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfElementLocated(regionContinueButton));
            if (continueBtn.isDisplayed()) {
                continueBtn.click();
            }
        } catch (Exception ignored) {
            // Cookie popup not present → safe to continue
        }
    }

    protected  void slowMode() {
        try {
            Thread.sleep(600);
        } catch (InterruptedException ignored) {}
    }
    protected WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    protected void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    protected void clickElement(WebElement element) {
        safeClick(element);
    }


}
