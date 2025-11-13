package com.company.ui.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import com.company.ui.utils.WebDriverFactory;

import java.io.FileInputStream;
import java.util.Properties;
import java.time.Duration;

public class BaseUiTest {
    protected static Properties cfg;
    protected WebDriver driver;

    @BeforeAll
    static void loadConfig() throws Exception {
        cfg = new Properties();
        cfg.load(new FileInputStream("src/test/resources/config.properties"));
    }

    @BeforeEach
    void setup() {
        String browser = cfg.getProperty("browser", "chrome");
        driver = WebDriverFactory.create(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @AfterEach
    void teardown() {
        if (driver != null) driver.quit();
    }
}
