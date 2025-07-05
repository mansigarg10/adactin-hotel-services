package com.mansi.adactin.tests;

import com.mansi.adactin.listeners.BaseTest;
import com.mansi.adactin.pages.EmailVerificationPage;
import com.mansi.adactin.pages.RegisterPage;
import com.mansi.adactin.utils.AdactinConstants;
import com.mansi.adactin.utils.AdactinTestConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * This class validates the Register functionality.
 *
 * @author Mansi Garg
 */
public class RegisterTest extends BaseTest {

    private static final Logger LOG = LogManager.getLogger(RegisterTest.class);

    private final String filePath = System.getProperty("user.dir") + AdactinConstants.REGISTER_JSON_PATH;

    @Test(dataProvider = "getData", priority = 1)
    public void registerToApplication(HashMap<String, String > input) throws IOException {
        driver = initializeBrowser();
        RegisterPage registerPage = new RegisterPage(driver);
        try {
            EmailVerificationPage emailVerificationPage = registerPage.fillRegistrationForm(input.get("Username"), input.get("Password"), input.get("FullName"), input.get("EmailAddress"));
            registerPage.waitForElementToBeClickable(emailVerificationPage.getConfirmationMessage());
            String verificationMsg = emailVerificationPage.getConfirmationMessage().getText();
            if (verificationMsg.contains(AdactinTestConstants.ACTUAL_VERIFICATION_MESSAGE)) {
                LOG.info("User registration is successful");
                Assert.assertTrue(true);
            } else {
                LOG.error("User registration failed: {}", verificationMsg);
                Assert.fail("User registration failed due to incorrect verification message.");
            }
        } catch (Exception e) {
            LOG.error("Registration failed due to exception: {}", e.getMessage());
            Assert.fail("Registration failed due to exception: " + e.getMessage());
        }
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data =  getJsonData(filePath);
        return new Object[][] {{data.getFirst()}};
    }

}
