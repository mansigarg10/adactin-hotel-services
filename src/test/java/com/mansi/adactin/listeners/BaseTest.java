package com.mansi.adactin.listeners;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mansi.adactin.utils.AdactinTestConstants;
import com.mansi.adactin.utils.HtmlFormatter;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * This class initializes and closes the browser after test execution is completed.
 * It also includes methods for taking screenshots and reading JSON data.
 *
 * @author Mansi Garg
 */
public class BaseTest {

    private final Logger LOG = LogManager.getLogger(BaseTest.class);

    public WebDriver driver;
    public Properties prop =  new Properties();
    public File propetyFile = new File(AdactinTestConstants.PROPERTY_FILE_PATH);

    /* This method initializes the browser based on the configuration specified in the properties file.
     * It opens the browser, maximizes the window, and sets an implicit wait.
     *
     * @return WebDriver instance for the initialized browser.
     * @throws IOException If an error occurs while reading the properties file.
     */
    public  WebDriver initializeBrowser() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(propetyFile);
        prop.load(fileInputStream);
        if (prop.getProperty("browser").equalsIgnoreCase("Chrome")) {
            driver = new ChromeDriver();
        } else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new EdgeDriver();
        }
        driver.get(AdactinTestConstants.HOTEL_WEBSITE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    /* This method reads JSON data from a specified file path and returns it as a list of HashMaps.
     *
     * @param filePath The path to the JSON file.
     * @return A list of HashMaps containing the JSON data.
     * @throws IOException If an error occurs while reading the file.
     */
    public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filePath), (Charset) null);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<>() {});
    }

    /* This method captures a screenshot of the current browser window.
     * It saves the screenshot to the specified path with the test name as the filename.
     *
     * @param driver   The WebDriver instance used to take the screenshot.
     * @param testName The name of the test, used as part of the screenshot filename.
     * @return The path where the screenshot is saved.
     * @throws IOException If an error occurs while saving the screenshot.
     */
    public static String captureScreenshot(WebDriver driver,String testName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationPath = new File(System.getProperty("user.dir")
                + AdactinTestConstants.SCREENSHOT_CAPTURE_PATH + testName + ".png");
        FileUtils.copyFile(src, destinationPath);
        return AdactinTestConstants.SCREENSHOT_CAPTURE_PATH + testName + ".png";
    }

    /**
     * This method is called after each test method execution.
     * It closes the browser to ensure that resources are released.
     */
    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    /**
     * This method is called after all test methods have been executed.
     * It formats the HTML report by removing leading empty lines and sanitizing image paths.
     */
    @AfterSuite
    public void cleanHtmlReport() {
        try {
            Path htmlPath = Paths.get(System.getProperty("user.dir") + "/docs/index.html");
            HtmlFormatter.removeLeadingEmptyLines(htmlPath);
            HtmlFormatter.sanitizeImagePaths(htmlPath);
            LOG.info("HTML report formatting completed.");
        } catch (IOException e) {
            LOG.error("Failed to format HTML report: {}", e.getMessage());
        }
    }

}
