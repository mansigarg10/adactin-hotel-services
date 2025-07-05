package com.mansi.adactin.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.mansi.adactin.utils.ExtentReporter;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

/*
 * This class implements ITestListener to handle test events and generate reports using ExtentReports.
 * It captures screenshots on test failure and logs test results.
 *
 * @author Mansi Garg
 */
public class Listeners extends BaseTest implements ITestListener {

    private final Logger LOG = LogManager.getLogger(BaseTest.class);

    private static final ExtentReports extent = ExtentReporter.report();

    ExtentTest extentTest;
    ThreadLocal<ExtentTest> eTest = new ThreadLocal<>();

    public void onTestStart(org.testng.ITestResult result) {
        extentTest = extent.createTest(result.getMethod().getMethodName());
        eTest.set(extentTest);
    }

    public void onTestSuccess(org.testng.ITestResult result) {
        LOG.info("Test passed: " + result.getName());
        eTest.get().log(Status.PASS, result.getName() + " is passed");
    }

    @Attachment
    public void onTestFailure(ITestResult result) {
        String filePath;
        eTest.get().fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            LOG.error("Exception occurred while getting driver instance: " + e.getMessage());
        }
        try {
            filePath = captureScreenshot(driver , result.getMethod().getMethodName());
        } catch (IOException ex) {
            LOG.error("Exception occurred while capturing screenshot: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
        eTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    public void onTestSkipped(org.testng.ITestResult result) {
        LOG.info("Test skipped: " + result.getName());
    }

    public void onFinish(ITestContext context) {
        extent.flush();
    }

}
