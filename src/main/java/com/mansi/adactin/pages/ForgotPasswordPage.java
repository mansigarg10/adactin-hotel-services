package com.mansi.adactin.pages;

import com.mansi.adactin.utils.AbstractComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains web elements of Forgot Password page.
 *
 * @author Mansi Garg
 */
@Getter
public class ForgotPasswordPage extends AbstractComponent {

    private WebDriver driver;
    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "emailadd_recovery")
    private WebElement enterEmailAddress;

    @FindBy(id = "Submit")
    private WebElement emailPasswordButton;

    @FindBy(id = "Reset")
    private WebElement resetButton;

    public PasswordResetConfirmationPage forgotPassword(String email) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.getForgotPasswordLink().click();
        enterEmailAddress.sendKeys(email);
        emailPasswordButton.click();
        return new PasswordResetConfirmationPage(driver);
    }

}
