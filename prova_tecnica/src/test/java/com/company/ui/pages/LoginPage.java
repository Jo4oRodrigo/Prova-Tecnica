package com.company.ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password");
    private By submitBtn = By.id("login-button");
    private By errorMsg = By.cssSelector(".error-message");

    public LoginPage(WebDriver driver, int timeoutSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    public void open(String baseUrl, String path) {
        driver.get(baseUrl + path);
    }

    public void setUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput)).clear();
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void submit() {
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }

    public String getErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg)).getText();
        } catch (TimeoutException e) {
            return null;
        }
    }
}
