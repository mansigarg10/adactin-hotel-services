package com.mansi.adactin.tests;

import com.mansi.adactin.listeners.BaseTest;
import com.mansi.adactin.pages.LoginPage;
import com.mansi.adactin.pages.SearchHotelPage;
import com.mansi.adactin.utils.AdactinConstants;
import com.mansi.adactin.utils.AdactinTestConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
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
    private SearchHotelPage searchHotelPage;

    @BeforeMethod
    public void openApplication() throws IOException {
        driver = initializeBrowser(AdactinTestConstants.HOTEL_WEBSITE_URL);
    }



    @Test ()
    public void performLoginWithValidCredentials() throws IOException {

        LoginPage loginPage = new LoginPage(driver);
        FileInputStream fileInputStream = new FileInputStream(propetyFile);
        prop.load(fileInputStream);
        try {
            searchHotelPage = loginPage.userLogin(prop.getProperty("username"), prop.getProperty("password"));
            if (searchHotelPage.getLocation().isDisplayed()) {
                LOG.info("Login successful for username: {}",prop.getProperty("username"));
                Assert.assertTrue(true);
            } else {
                LOG.error("Login failed for username: {}", prop.getProperty("username"));
                Assert.fail("Login failed due to incorrect credentials.");
            }
        } catch (Exception e) {
            LOG.error("Login failed");
            Assert.fail("Login failed due to exception: " + e.getMessage());
        }
    }



    @Test(dataProvider = "getInValidData")
    public void loginWithInvalidCredentials(HashMap<String, String> input1) {
        LoginPage loginPage = new LoginPage(driver);
        try {
            searchHotelPage = loginPage.userLogin(input1.get("username"), input1.get("password"));
            String errorMessage = loginPage.getLoginErrorMessage().getText();
            Assert.assertEquals(errorMessage, AdactinTestConstants.LOGIN_ERROR_MESSAGE);
        } catch (Exception e) {
            LOG.error("Error message not displayed for invalid login");
            Assert.fail("Error message is not populating: " + e.getMessage());
        }
    }

    @DataProvider
    public Object[][] getInValidData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(filePath);
        return new Object[][]{{data.get(1)}};
    }
}
