package utils;


import driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static java.lang.String.format;

public class TestListener implements ITestListener {
    private final static Logger LOG = LogManager.getRootLogger();

    @Override
    public void onTestStart(ITestResult result) {
        LOG.info("{} started", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOG.info("{} passed", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOG.info("{} failed", result.getName());
        takeScreenshot();
    }

    private void takeScreenshot(){
        File screenshot = ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(format(".//target/screenshots/%s.png", LocalDate.now())));
        } catch (IOException e) {
            LOG.error("Failed to save screenshot: {}", e.getMessage());
        }
    }
}
