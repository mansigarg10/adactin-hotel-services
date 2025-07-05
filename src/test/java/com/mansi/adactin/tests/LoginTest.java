package com.mansi.adactin.tests;

import com.mansi.adactin.listeners.BaseTest;
import com.mansi.adactin.pages.LoginPage;
import com.mansi.adactin.pages.SearchHotelPage;
import com.mansi.adactin.utils.AdactinConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * This class validates the Login functionality.
 *
 * @author Mansi Garg
 */
public class LoginTest extends BaseTest {

    private final Logger LOG = LogManager.getLogger(LoginTest.class);

    private final String filePath = System.getProperty("user.dir") + AdactinConstants.LOGIN_JSON_PATH;

    @Test (dataProvider = "getData")
    public void performLogin(HashMap<String ,String > input) throws IOException {
        driver = initializeBrowser();
        LoginPage loginPage = new LoginPage(driver);
        try {
            SearchHotelPage searchHotelPage = loginPage.userLogin(input.get("username"), input.get("password"));
            if (searchHotelPage.getLocation().isDisplayed()) {
                LOG.info("Login successful for username: {}", input.get("username"));
                Assert.assertTrue(true);
            } else {
                LOG.error("Login failed for username: {}", input.get("username"));
                Assert.fail("Login failed due to incorrect credentials.");
            }
        } catch (Exception e) {
            LOG.error("Login failed");
            Assert.fail("Login failed due to exception: " + e.getMessage());
        }
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(filePath);
        return new Object[][]{{data.get(0)},{data.get(1)}};
    }

}
