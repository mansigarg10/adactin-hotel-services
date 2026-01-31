package com.mansi.adactin.pages;

import com.mansi.adactin.utils.AbstractComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains web elements of Change Password page.
 *
 * @author Mansi Garg
 */
@Getter
public class ChangePasswordPage extends AbstractComponent {

    private final WebDriver driver;
    private SearchHotelPage searchHotelPage;

    public ChangePasswordPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "current_pass")
    private WebElement currentPassword;

    @FindBy(id = "new_password")
    private WebElement newPassword;

    @FindBy(id = "re_password")
    private WebElement confirmPassword;

    @FindBy(id = "Submit")
    private WebElement submitButton;

    public void changePassword(String username, String oldPassword, String newPassword) {
        LoginPage loginPage = new LoginPage(driver);
        searchHotelPage = loginPage.userLogin(username, oldPassword);
        searchHotelPage.getChangePassword().click();
        getCurrentPassword().sendKeys(oldPassword);
        getNewPassword().sendKeys(newPassword);
        getConfirmPassword().sendKeys(newPassword);
        getSubmitButton().click();
    }

}
