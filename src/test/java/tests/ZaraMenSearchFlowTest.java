package tests;

import config.SeleniumConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.MenuMensPage;
import pages.SearchMensPage;
import utils.ExcelReader;

public class ZaraMenSearchFlowTest {

    private MenuMensPage menuPage;
    private SearchMensPage searchPage;
    private ExcelReader excel;

    @BeforeEach
    void setUp() {
        // 1) siteyi aç
        SeleniumConfig.getDriver().get("https://www.zara.com/tr/");

        // 2) page nesneleri
        menuPage = new MenuMensPage();
        searchPage = new SearchMensPage();

        // 3) excel dosyasını aç
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
    void menCategory_shortThenShirtSearch_flow() {

        menuPage.navigateToMenViewAll();  // popup + cookie + menü adımları

        searchPage.openSearch();

        String shortText = excel.getCell(0, 0);   // "şort"
        String shirtText = excel.getCell(1, 0);   // "gömlek"

        searchPage.typeKeyword(shortText); // şort yaz

        searchPage.clearSearchInput(); // temizle

        searchPage.typeKeyword(shirtText); // gömlek yaz

        searchPage.submitSearch(); // ara

    }
}
