package com.example.framework.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static synchronized void initReports(String reportsDir) {
        if (extent == null) {
            new File(reportsDir).mkdirs();
            ExtentHtmlReporter html = new ExtentHtmlReporter(reportsDir + "/extent-report.html");
            extent = new ExtentReports();
            extent.attachReporter(html);
        }
    }

    public static ExtentTest createTest(String name) {
        ExtentTest t = extent.createTest(name);
        test.set(t);
        return t;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void flushReports() {
        if (extent != null) extent.flush();
        extent = null;
    }
}
