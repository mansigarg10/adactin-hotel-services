package com.mansi.adactin.tests;

import com.mansi.adactin.listeners.BaseTest;
import com.mansi.adactin.pages.ChangePasswordPage;
import com.mansi.adactin.utils.AdactinConstants;
import com.mansi.adactin.utils.AdactinTestConstants;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * This class validates the change password functionality.
 *
 * @author Mansi Garg
 */
public class ChangePasswordTest extends BaseTest {



    @BeforeMethod
    public void openApplication() throws IOException {
        driver = initializeBrowser(AdactinTestConstants.HOTEL_WEBSITE_URL);

    }

    @Test( priority = 5)
    public void changePassword() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(propetyFile);
        prop.load(fileInputStream);
        ChangePasswordPage changePasswordPage = new ChangePasswordPage(driver);
        String newPassword = passwordStamp();
        changePasswordPage.changePassword(prop.getProperty("username"), prop.getProperty("password"),newPassword);
        prop.setProperty("password",newPassword);
        prop.store(new FileOutputStream(propetyFile), null);

    }




    private String passwordStamp(){
        String dynamicPassword = "Michella" + System.currentTimeMillis()%1000 +23;
        return dynamicPassword;
    }


}
