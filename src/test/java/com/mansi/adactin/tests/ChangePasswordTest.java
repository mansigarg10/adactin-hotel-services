package com.mansi.adactin.tests;

import com.mansi.adactin.listeners.BaseTest;
import com.mansi.adactin.pages.ChangePasswordPage;
import com.mansi.adactin.utils.AdactinTestConstants;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * This class validates the change password functionality.
 *
 * @author Mansi Garg
 */
public class ChangePasswordTest extends BaseTest {

    @Test(priority = 5, enabled = false)
    public void changePassword() throws IOException {
        driver = initializeBrowser();
        FileInputStream fileInputStream = new FileInputStream(propetyFile);
        prop.load(fileInputStream);
        ChangePasswordPage changePasswordPage = new ChangePasswordPage(driver);
        changePasswordPage.changePassword(prop.getProperty("username"), prop.getProperty("password"), AdactinTestConstants.DUMMY_PASSWORD);
    }

}
