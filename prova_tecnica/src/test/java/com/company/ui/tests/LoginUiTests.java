package com.company.ui.tests;

import com.company.ui.pages.LoginPage;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginUiTests extends BaseUiTest {
    private int timeout;

    @BeforeEach
    void beforeEach() {
        timeout = Integer.parseInt(cfg.getProperty("timeout.seconds", "10"));
    }

    @Test
    void shouldLoginWithValidUser() {
        LoginPage login = new LoginPage(driver, timeout);
        login.open(cfg.getProperty("base.url"), cfg.getProperty("login.path"));
        login.setUsername("user@example.com");
        login.setPassword("validPassword");
        login.submit();

        new WebDriverWait(driver, Duration.ofSeconds(timeout))
            .until(d -> d.getCurrentUrl().contains("/dashboard"));

        assertTrue(driver.getCurrentUrl().contains("/dashboard"));
    }

    @Test
    void visitorShouldBeDeniedAccess() {
        LoginPage login = new LoginPage(driver, timeout);
        login.open(cfg.getProperty("base.url"), cfg.getProperty("login.path"));
        login.setUsername("visitor@example.com");
        login.setPassword("visitorPass");
        login.submit();

        new WebDriverWait(driver, Duration.ofSeconds(timeout))
            .until(d -> d.getCurrentUrl().contains("/access-denied") || d.getPageSource().contains("Acesso negado"));

        assertTrue(driver.getCurrentUrl().contains("/access-denied") || driver.getPageSource().contains("Acesso negado"));
    }

    @Test
    void shouldLockAccountAfterThreeFailedAttempts() throws InterruptedException {
        LoginPage login = new LoginPage(driver, timeout);
        login.open(cfg.getProperty("base.url"), cfg.getProperty("login.path"));
        String username = "toBeLocked@example.com";

        for (int i = 1; i <= 3; i++) {
            login.setUsername(username);
            login.setPassword("wrongPassword" + i);
            login.submit();
            Thread.sleep(500);
        }

        login.setUsername(username);
        login.setPassword("correctPassword");
        login.submit();

        String err = login.getErrorMessage();
        assertNotNull(err);
        assertTrue(err.toLowerCase().contains("bloquead") || err.contains("423"));
    }
}
