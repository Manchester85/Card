package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.openqa.selenium.By.cssSelector;

class BankCardOrderTest {

    WebDriver driver;

    @BeforeAll
        static void setupClass() {
            WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

    }

    @AfterEach
    void teardown() {
        driver.quit();
        driver = null;
    }


    @Test
    void shouldTestV1() {
        driver.get("http://localhost:9999");
        WebElement form = driver.findElement(By.cssSelector("[action]"));
        driver.findElement(cssSelector("[data-test-id=name] input")).sendKeys("Ирина Петрова");
        driver.findElement(cssSelector("[data-test-id=phone] input")).sendKeys("+79253336677");
        driver.findElement(cssSelector("[data-test-id=agreement] .checkbox__box")).click();
        driver.findElement(cssSelector("[role=button]")).click();
        String actualMessage = driver.findElement(cssSelector("[data-test-id=order-success]")).getText();
        String expectedMessage = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expectedMessage, actualMessage.trim(), "При отправке заявки произошла ошибка.");
    }

}

