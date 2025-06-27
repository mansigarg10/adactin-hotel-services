package com.mansi.adactin.tests;

import com.mansi.adactin.listeners.BaseTest;
import com.mansi.adactin.pages.ForgotPasswordPage;
import com.mansi.adactin.pages.PasswordResetConfirmationPage;
import com.mansi.adactin.utils.AdactinTestConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * This class validates the forgot password functionality.
 *
 * @author Mansi Garg
 */
public class ForgetPasswordTest extends BaseTest {

    private PasswordResetConfirmationPage passwordResetConfirmationPage;

    @Test(priority = 4)
    public void forgotYourPassword() throws IOException {
        driver =   initializeBrowser();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        passwordResetConfirmationPage = forgotPasswordPage.forgotPassword(AdactinTestConstants.EMAIL);
        String resetSuccessMsg = passwordResetConfirmationPage.getResetPasswordSuccessMessage().getText();
        System.out.println(resetSuccessMsg);
        if (resetSuccessMsg.contains(AdactinTestConstants.FORGOT_PASSWORD_SUCCESS_MESSAGE)) {
            Assert.assertTrue(true);
            System.out.println(AdactinTestConstants.FORGOT_PASSWORD_SUCCESS_MESSAGE);
        } else {
            Assert.fail();
        }
    }

}
