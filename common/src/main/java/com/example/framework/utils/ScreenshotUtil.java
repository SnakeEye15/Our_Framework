package com.example.framework.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String captureScreenshot(WebDriver driver, String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String dir = "target/reports/screenshots";
            new File(dir).mkdirs();
            String path = Paths.get(dir, name + "_" + timestamp + ".png").toString();
            FileUtils.copyFile(src, new File(path));
            return path;
        } catch (Exception e) {
            return null;
        }
    }
}
