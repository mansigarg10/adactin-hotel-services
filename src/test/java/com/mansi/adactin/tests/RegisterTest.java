package com.mansi.adactin.tests;

import com.mansi.adactin.listeners.BaseTest;
import com.mansi.adactin.pages.EmailVerificationPage;
import com.mansi.adactin.pages.RegisterPage;
import com.mansi.adactin.utils.AdactinConstants;
import com.mansi.adactin.utils.AdactinTestConstants;
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

    private RegisterPage registerPage;
    private EmailVerificationPage emailVerificationPage;

    private final String filePath = System.getProperty("user.dir") + AdactinConstants.REGISTER_JSON;

    @Test(dataProvider = "getData", priority = 1)
    public void registerToApplication(HashMap<String, String > input) throws IOException, InterruptedException {
        driver = initializeBrowser();
        registerPage = new RegisterPage(driver);
        emailVerificationPage = registerPage.fillRegistrationForm(input.get("Username"),input.get("Password"),input.get("FullName"),input.get("EmailAddress"));
        String verificationMsg = emailVerificationPage.getConfirmationMessage().getText();
        if (verificationMsg.contains(AdactinTestConstants.ACTUAL_VERIFICATION_MESSAGE)) {
            Assert.assertTrue(true);
            System.out.println(AdactinTestConstants.ACTUAL_VERIFICATION_MESSAGE);
        } else {
            Assert.assertTrue(false);
        }
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data =  getJsonData(filePath);
        return new Object[][] {{data.getFirst()}};
    }

}
