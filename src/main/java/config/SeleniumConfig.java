package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class SeleniumConfig {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {

        if (driver.get() == null) {

            DriverType type = DriverType.valueOf(ConfigReader.get("browser"));
            boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));

            switch (type) {

                case CHROME -> {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");

                    if (headless) chromeOptions.addArguments("--headless=new");

                    driver.set(new ChromeDriver(chromeOptions));
                }

                case FIREFOX -> {
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (headless) firefoxOptions.addArguments("--headless");

                    driver.set(new FirefoxDriver(firefoxOptions));
                }

                case EDGE -> {
                    WebDriverManager.edgedriver().setup();
                    driver.set(new EdgeDriver());
                }
            }
        }

        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
