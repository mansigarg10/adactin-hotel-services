package com.mansi.adactin.tests;

import com.mansi.adactin.listeners.BaseTest;
import com.mansi.adactin.pages.ForgotPasswordPage;
import com.mansi.adactin.pages.PasswordResetConfirmationPage;
import com.mansi.adactin.utils.AdactinTestConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * This class validates the forgot password functionality.
 *
 * @author Mansi Garg
 */
public class ForgetPasswordTest extends BaseTest {

    private static final Logger LOG = LogManager.getLogger(ForgetPasswordTest.class);

    @Test(priority = 4)
    public void forgotYourPassword() throws IOException {
        driver = initializeBrowser();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        PasswordResetConfirmationPage passwordResetConfirmationPage = forgotPasswordPage.forgotPassword(AdactinTestConstants.EMAIL);
        String resetSuccessMsg = passwordResetConfirmationPage.getResetPasswordSuccessMessage().getText();
        try {
            if (resetSuccessMsg.contains(AdactinTestConstants.FORGOT_PASSWORD_SUCCESS_MESSAGE)) {
                Assert.assertTrue(true);
                LOG.info("Forgot password successful for email: {}", AdactinTestConstants.EMAIL);
            } else {
                Assert.fail();
            }
        } catch (Exception e) {
            LOG.error("Forgot password failed due to exception");
            Assert.fail("Forgot password failed due to exception: " + e.getMessage());
        }
    }

}
