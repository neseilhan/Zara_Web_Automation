package tests;

import config.ConfigReader;
import config.SeleniumConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.*;
import utils.ExcelReader;
import utils.TextWriter;

public class ZaraMenSearchFlowTest {

    private static final Logger logger = LogManager.getLogger(ZaraMenSearchFlowTest.class);
    private HomePage homePage;
    private LoginPage loginPage;
    private OrderPage orderPage;
    private MenuPage menuPage;
    private SearchPage searchPage;
    private ProductPage productPage;
    private ExcelReader excel;
    private CartPage cartPage;


    @BeforeEach
    void setUp() {
        logger.info("Test setup başlatılıyor...");
        SeleniumConfig.getDriver().get(ConfigReader.getBaseUrl());

        orderPage = new OrderPage();
        homePage   = new HomePage();
        loginPage  = new LoginPage();
        menuPage = new MenuPage();
        searchPage = new SearchPage();
        productPage = new ProductPage();
        cartPage = new CartPage();


        excel = new ExcelReader("test-data/search-data.xlsx", "Sayfa1");
        logger.info("Test setup tamamlandı.");
    }

    @AfterEach
    void tearDown() {
        logger.info("Test teardown başlatılıyor...");
        if (excel != null) {
            excel.close();
        }
        SeleniumConfig.quitDriver();
        logger.info("Test teardown tamamlandı.");
    }

    @Test
    void menCategory_shortThenShirtSearch_flow() throws InterruptedException {
        logger.info("Test: menCategory_shortThenShirtSearch_flow başlatılıyor...");

        homePage.acceptCookies();
        logger.info("Çerezler kabul edildi.");

        homePage.goToLoginPage();
        logger.info("Login sayfasına yönlendirildi.");

        loginPage.login(ConfigReader.getEmail(), ConfigReader.getPassword());
        logger.info("Login işlemi tamamlandı.");

        orderPage.openBurgerMenu();
        logger.info("Burger menü açıldı.");

        menuPage.navigateToMenViewAll();
        logger.info("Menüden Erkek kategorisine geçildi.");

        searchPage.openSearch();
        logger.info("Arama ekranı açıldı.");

        String shortText = excel.getCell(0, 0);
        String shirtText = excel.getCell(1, 0);

        logger.info("Arama için kısa ürün adı: {}", shortText);
        searchPage.typeKeyword(shortText);
        searchPage.clearSearchInput();

        logger.info("Arama için gömlek ürün adı: {}", shirtText);
        searchPage.typeKeyword(shirtText);
        searchPage.submitSearch();

        Thread.sleep(1500);

        searchPage.selectRandomProduct();
        logger.info("Rastgele bir ürün seçildi.");

        productPage.waitForProductPageToLoad();

        String name = productPage.getProductName();
        String price = productPage.getProductPrice();
        logger.info("Seçilen ürün: {}, Fiyat: {}", name, price);

        TextWriter.writeProductInfo("selected-product.txt", name, price);
        logger.info("Ürün bilgisi dosyaya yazıldı.");

        productPage.selectSize();
        productPage.goToBasketFromHeader();

        String cartPrice = cartPage.getCartPrice();
        logger.info("Sepet fiyatı: {}", cartPrice);
        assert cartPrice.equals(price) : "Fiyatlar eşleşmiyor!";

        cartPage.increaseQuantity();
        logger.info("Ürün adedi artırıldı.");

        assert cartPage.getQuantity() == 2 : "Adet 2 olmalıydı!";
        logger.info("Ürün adedi doğrulandı.");

        cartPage.removeItem();
        logger.info("Ürün sepetten kaldırıldı.");

    }
}