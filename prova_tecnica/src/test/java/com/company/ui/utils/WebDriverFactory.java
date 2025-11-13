package com.company.ui.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    public static WebDriver create(String browser) {
        if ("chrome".equalsIgnoreCase(browser)) {
            ChromeOptions opts = new ChromeOptions();
            // In CI use headless mode by setting a system property or env var
            if ("true".equalsIgnoreCase(System.getProperty("headless", "false"))) {
                opts.addArguments("--headless=new");
                opts.addArguments("--no-sandbox");
                opts.addArguments("--disable-dev-shm-usage");
            }
            return new ChromeDriver(opts);
        }
        throw new IllegalArgumentException("Browser não suportado: " + browser);
    }
}
