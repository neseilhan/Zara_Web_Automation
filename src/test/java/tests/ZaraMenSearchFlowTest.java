package tests;

import config.ConfigReader;
import config.SeleniumConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.*;
import utils.ExcelReader;
import utils.TextWriter;

public class ZaraMenSearchFlowTest {

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
        SeleniumConfig.getDriver().get(ConfigReader.getBaseUrl());

        orderPage = new OrderPage();
        homePage   = new HomePage();
        loginPage  = new LoginPage();
        menuPage = new MenuPage();
        searchPage = new SearchPage();
        productPage = new ProductPage();
        cartPage = new CartPage();


        excel = new ExcelReader("test-data/search-data.xlsx", "Sayfa1");
    }

    @AfterEach
    void tearDown() {
        if (excel != null) {
            excel.close();
        }
        SeleniumConfig.quitDriver();
    }

    @Test
    void menCategory_shortThenShirtSearch_flow() throws InterruptedException {

        homePage.acceptCookies();
        homePage.goToLoginPage();

        loginPage.login(
                ConfigReader.getEmail(),
                ConfigReader.getPassword()
        );
        orderPage.openBurgerMenu();

        menuPage.navigateToMenViewAll();

        searchPage.openSearch();

        String shortText = excel.getCell(0, 0);
        String shirtText = excel.getCell(1, 0);

        searchPage.typeKeyword(shortText);
        searchPage.clearSearchInput();

        searchPage.typeKeyword(shirtText);
        searchPage.submitSearch();

        Thread.sleep(1500);

        searchPage.selectRandomProduct();

        productPage.waitForProductPageToLoad();

        String name = productPage.getProductName();
        String price = productPage.getProductPrice();

        TextWriter.writeProductInfo("selected-product.txt", name, price);

        productPage.selectSize();
        productPage.goToBasketFromHeader();

        String cartPrice = cartPage.getCartPrice();
        assert cartPrice.equals(price) : "Fiyatlar eşleşmiyor!";

        cartPage.increaseQuantity();

        assert cartPage.getQuantity() == 2 : "Adet 2 olmalıydı!";

        cartPage.removeItem();

    }
}