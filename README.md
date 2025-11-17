🛍️ Zara UI Test Automation Project

Bu proje, Selenium WebDriver + Java + Maven + JUnit5 kullanılarak hazırlanmış uçtan uca bir UI test otomasyon projesidir.

Test akışı:
Login → Menü → Arama → Random ürün seçimi → Beden seçimi → Sepete ekleme → Adet artırma → Ürün silme → Sepetin boş olduğunu doğrulama

Loom video demosu (eklenecek):
👉 Loom Video – Test Automation Demo

## Teknolojiler
| Teknoloji                    | Açıklama                         |
| ---------------------------- | -------------------------------- |
| **Java 21**                  | Ana dil                          |
| **Selenium WebDriver**       | UI otomasyon                     |
| **JUnit 5**                  | Test framework                   |
| **Maven**                    | Build + dependency yönetimi      |
| **WebDriverManager**         | Driver kurulumu                  |
| **Page Object Model (POM)**  | Sayfa bazlı mimari               |
| **ExcelReader (Apache POI)** | Test datası Excel’den            |
| **TextWriter**               | Ürün bilgisini TXT’ye yazma      |
| **ConfigReader**             | Gizli bilgiler & config yönetimi |

## Proje Yapısı

 ├─ test
 │   ├── java
 │   │    ├── pages
 │   │    │    ├── HomePage.java
 │   │    │    ├── LoginPage.java
 │   │    │    ├── MenuPage.java
 │   │    │    ├── SearchPage.java
 │   │    │    ├── ProductPage.java
 │   │    │    └── CartPage.java
 │   │    ├── base
 │   │    │    └── BasePage.java
 │   │    ├── config
 │   │    │    ├── SeleniumConfig.java
 │   │    │    └── ConfigReader.java
 │   │    ├── utils
 │   │    │    ├── ExcelReader.java
 │   │    │    └── TextWriter.java
 │   │    └── tests
 │   │         └── ZaraMenSearchFlowTest.java
 │   │
 │   └── resources
 │        ├── config.properties     
 │        └── search-data.xlsx  

  └─ selected-product.txt (Test sırasında seçilen ürün bilgisi burada saklanır)
## Kurulum ve Çalıştırma    
git clone https://github.com/kullanici/zara-automation.git
cd zara-automation

## Testi çalıştırmak için Maven kullanabilirsiniz:
mvn clean test


  