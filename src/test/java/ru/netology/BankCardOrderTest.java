package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.openqa.selenium.By.*;

class BankCardOrderTest {

    WebDriver driver;

    @BeforeAll
        static void setupClass() {
            WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
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
        List<WebElement> textFields = driver.findElements(className("input__control"));
        textFields.get(0).sendKeys("Ирина Петрова");
        textFields.get(1).sendKeys("+79253336677");
        driver.findElement(className("checkbox__box")).click();
        driver.findElement(tagName("button")).click();
        String actualMessage = driver.findElement(cssSelector("[data-test-id=order-success]")).getText().strip();
        String expectedMessage = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expectedMessage, actualMessage, "При отправке заявки произошла ошибка.");
    }

}

