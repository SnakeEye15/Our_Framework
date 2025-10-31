package com.example.tests.ui;

import com.example.framework.base.BaseTest;
import com.example.framework.config.ConfigManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Demo login test on The Internet (Herokuapp)")
    public void testLogin() {
        driver.get(ConfigManager.get("web.baseUrl") + "/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String flash = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(flash.toLowerCase().contains("you logged into a secure area"));
    }
}
