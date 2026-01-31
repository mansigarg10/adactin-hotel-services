package com.mansi.adactin.tests;

import com.mansi.adactin.listeners.BaseTest;
import com.mansi.adactin.pages.EmailVerificationPage;
import com.mansi.adactin.pages.RegisterPage;
import com.mansi.adactin.utils.AdactinConstants;
import com.mansi.adactin.utils.AdactinTestConstants;
import com.mansi.adactin.utils.CaptchaReader;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.apache.commons.compress.archivers.StreamingNotSupportedException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.pqc.crypto.ntru.NTRUKEMExtractor;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
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


    @BeforeMethod
    public void openApplication() throws IOException {
        driver = initializeBrowser(AdactinTestConstants.HOTEL_WEBSITE_URL);
    }

    @Test(priority = 1)
    public void registerToApplication() throws IOException {
        RegisterPage registerPage = new RegisterPage(driver);
        try {
            EmailVerificationPage emailVerificationPage = registerPage.fillRegistrationForm(usernameStamp(), AdactinTestConstants.DUMMY_PASSWORD, usernameStamp(), emailStamp());
            String text = CaptchaReader.readCaptchaImage(registerPage.getImageElement(), AdactinTestConstants.CAPTCHA_STORED_PATH);
            Thread.sleep(3000);
            System.out.println("Captcha detected: " + text);
            Thread.sleep(3000);
            driver.findElement(By.id("captcha-form")).sendKeys(text);
            registerPage.getTermsAndConditionsCheckbox().click();
            registerPage.getRegisterButton().click();
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



    private static String usernameStamp(){
        String dynamicUsername = "user" + System.currentTimeMillis()%10000;
        return dynamicUsername.replaceAll("[^a-zA-Z0-9]", "");
    }

    private static String emailStamp(){
        String dynamicEmail = "email" + System.currentTimeMillis()%10000 + "@test.com";
        return dynamicEmail;
    }



}
