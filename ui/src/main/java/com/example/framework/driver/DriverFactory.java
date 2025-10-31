package com.example.framework.driver;

import com.example.framework.config.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(createDriver());
        }
        return driver.get();
    }

    private static WebDriver createDriver() {
        String browser = ConfigManager.get("browser", "chrome");
        boolean headless = ConfigManager.getBoolean("headless", true);

        try {
            if ("firefox".equalsIgnoreCase(browser)) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions fo = new FirefoxOptions();
                fo.setHeadless(headless);
                return new FirefoxDriver(fo);
            }
            WebDriverManager.chromedriver().setup();
            ChromeOptions opt = new ChromeOptions();
            if (headless) opt.addArguments("--headless=new", "--disable-gpu");
            opt.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            return new ChromeDriver(opt);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create WebDriver", e);
        }
    }

    public static void quitDriver() {
        WebDriver d = driver.get();
        if (d != null) {
            try { d.quit(); } catch (Exception ignored) {}
            driver.remove();
        }
    }
}
