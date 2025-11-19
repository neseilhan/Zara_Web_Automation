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
        logger.info("Test setup baslatiliyor...");
        SeleniumConfig.getDriver().get(ConfigReader.getBaseUrl());

        orderPage = new OrderPage();
        homePage   = new HomePage();
        loginPage  = new LoginPage();
        menuPage = new MenuPage();
        searchPage = new SearchPage();
        productPage = new ProductPage();
        cartPage = new CartPage();


        excel = new ExcelReader("test-data/search-data.xlsx", "Sayfa1");
        logger.info("Test setup tamamlandi.");
    }

    @AfterEach
    void tearDown() {
        logger.info("Test teardown baslatiliyor...");
        if (excel != null) {
            excel.close();
        }
        SeleniumConfig.quitDriver();
        logger.info("Test teardown tamamlandı.");
    }

    @Test
    void menCategory_shortThenShirtSearch_flow() throws InterruptedException {
        logger.info("Test: menCategory_shortThenShirtSearch_flow baslatiliyor...");

        homePage.acceptCookies();
        logger.info("Cerezler kabul edildi.");

        homePage.goToLoginPage();
        logger.info("Login sayfasina yonlendirildi.");

        loginPage.login(ConfigReader.getEmail(), ConfigReader.getPassword());
        logger.info("Login islemi tamamlandi.");

        orderPage.openBurgerMenu();
        logger.info("Burger menu acildi.");

        menuPage.navigateToMenViewAll();
        logger.info("Menuden Erkek kategorisine gecildi.");

        searchPage.openSearch();
        logger.info("Arama ekrani acildi.");

        String shortText = excel.getCell(0, 0);
        String shirtText = excel.getCell(1, 0);

        logger.info("Arama icin kisa urun adi: {}", shortText);
        searchPage.typeKeyword(shortText);
        searchPage.clearSearchInput();

        logger.info("Arama icin gömlek urun adi: {}", shirtText);
        searchPage.typeKeyword(shirtText);
        searchPage.submitSearch();

        Thread.sleep(1500);

        searchPage.selectRandomProduct();
        logger.info("Rastgele bir urun secildi.");

        productPage.waitForProductPageToLoad();

        String name = productPage.getProductName();
        String price = productPage.getProductPrice();
        logger.info("Secilen urun: {}, Fiyat: {}", name, price);

        TextWriter.writeProductInfo("selected-product.txt", name, price);
        logger.info("Urun bilgisi dosyaya yazildi.");

        productPage.selectSize();
        productPage.goToBasketFromHeader();

        String cartPrice = cartPage.getCartPrice();
        logger.info("Sepet fiyati: {}", cartPrice);
        assert cartPrice.equals(price) : "Fiyatlar eslesmiyor!";

        cartPage.increaseQuantity();
        logger.info("Urun adedi artırıldı.");

        assert cartPage.getQuantity() == 2 : "Adet 2 olmaliydi!";
        logger.info("Urun adedi dogrulandi.");

        cartPage.removeItem();
        logger.info("Urun sepetten kaldirildi.");

    }
}