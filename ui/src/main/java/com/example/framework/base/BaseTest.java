package com.example.framework.base;

import com.example.framework.config.ConfigManager;
import com.example.framework.driver.DriverFactory;
import com.example.framework.reporting.ExtentManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp(ITestContext ctx) {
        driver = DriverFactory.getDriver();
        ctx.setAttribute("driver", driver);
        ExtentManager.initReports(ConfigManager.get("reports.dir", "target/ui-reports"));
        ExtentManager.createTest(getClass().getSimpleName());
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
        ExtentManager.flushReports();
    }
}
