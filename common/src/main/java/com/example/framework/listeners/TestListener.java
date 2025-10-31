package com.example.framework.listeners;

import com.aventstack.extentreports.Status;
import com.example.framework.reporting.ExtentManager;
import com.example.framework.utils.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        ExtentManager.createTest(result.getMethod().getMethodName());
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().log(Status.PASS, "Test passed");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        Object driverObj = result.getTestContext().getAttribute("driver");
        String path = null;
        if (driverObj instanceof WebDriver) {
            path = ScreenshotUtil.captureScreenshot((WebDriver) driverObj, result.getMethod().getMethodName());
        }
        ExtentManager.getTest().log(Status.FAIL, result.getThrowable());
        if (path != null) {
            try { ExtentManager.getTest().addScreenCaptureFromPath(path); } catch (Exception ignored) {}
        }
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.getTest().log(Status.SKIP, "Test skipped");
    }
    @Override
    public void onStart(ITestContext context) {}
    @Override
    public void onFinish(ITestContext context) {}
}
