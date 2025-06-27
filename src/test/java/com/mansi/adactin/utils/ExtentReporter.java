package com.mansi.adactin.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

/* This class is responsible for configuring and generating ExtentReports.
 * It sets up the report with the specified configurations such as report name, document title, and system information.
 *
 * @author Mansi Garg
 */
public  class ExtentReporter {

    public static ExtentReports report() {
        String filePath = AdactinTestConstants.Extent_REPORTS_PATH;
        ExtentSparkReporter  reporter = new ExtentSparkReporter(new File(filePath));
        reporter.config().setReportName(AdactinTestConstants.TEST_REPORT_NAME);
        reporter.config().setDocumentTitle(AdactinTestConstants.DOCUMENT_TITLE);
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo(AdactinTestConstants.K_WINDOWS_VERSION, AdactinTestConstants.WINDOWS_VERSION);
        extent.setSystemInfo(AdactinTestConstants.K_TESTER_NAME, AdactinTestConstants.TESTER_NAME);
        extent.setSystemInfo(AdactinTestConstants.K_BROWSER_NAME, AdactinTestConstants.BROWSER_NAME);
        extent.setSystemInfo(AdactinTestConstants.K_JAVA_VERSION, AdactinTestConstants.JAVA_VERSION);
        return extent;
    }

}
