package com.mansi.adactin.pages;

import com.mansi.adactin.utils.AbstractComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains web elements of register page.
 *
 * @author Mansi Garg
 */
@Getter
public class RegisterPage extends AbstractComponent {

    private WebDriver driver;

    public RegisterPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "username")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "re_password")
    private WebElement confirmPassword;

    @FindBy(id = "full_name")
    private WebElement fullName;

    @FindBy(id = "email_add")
    private WebElement emailAddress;

    @FindBy(xpath = "//a[@id='change-image']")
    private WebElement changeImageLink;

    @FindBy(id = "captcha-form")
    private WebElement captchaText;

    @FindBy(xpath = "//input[@id='tnc_box']")
    private WebElement termsAndConditionsCheckbox;

    @FindBy(id = "Submit")
    private WebElement registerButton;

    @FindBy(xpath = "//td[@class='reg_success']")
    private WebElement registrationSuccessMessage;

    public EmailVerificationPage fillRegistrationForm(String username, String pass, String name,
                                                      String emailId) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.getRegisterLink().click();
        userName.sendKeys(username);
        password.sendKeys(pass);
        confirmPassword.sendKeys(pass);
        fullName.sendKeys(name);
        emailAddress.sendKeys(emailId);
        Thread.sleep(8000);
        termsAndConditionsCheckbox.click();
        registerButton.click();
        return new EmailVerificationPage(driver);
    }

}
