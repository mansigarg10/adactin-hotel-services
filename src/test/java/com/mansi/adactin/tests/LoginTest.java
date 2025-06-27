package com.mansi.adactin.tests;

import com.mansi.adactin.listeners.BaseTest;
import com.mansi.adactin.pages.LoginPage;
import com.mansi.adactin.utils.AdactinConstants;
import org.openqa.selenium.By;
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

    private WebDriver driver;
    private LoginPage loginPage;

    private String filePath = System.getProperty("user.dir") + AdactinConstants.LOGIN_JSON;

    @Test (dataProvider = "getData")
    public void performLogin(HashMap<String ,String > input) throws IOException {
        driver = initializeBrowser();
        loginPage = new LoginPage(driver);
        loginPage.userLogin(input.get("username"),input.get("password"));
        String welcomeMsg = driver.findElement(By.cssSelector("table.content td[class$='welcome_menu']:first-child")).getText();
        Assert.assertEquals(welcomeMsg,"Welcome to Adactin Group of Hotels");
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(filePath);
        return new Object[][]{{data.get(0)},{data.get(1)}};
    }

}
