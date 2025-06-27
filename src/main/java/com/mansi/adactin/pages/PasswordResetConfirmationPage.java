package com.mansi.adactin.pages;

import com.mansi.adactin.utils.AbstractComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains web elements of Password reset page.
 *
 * @author Mansi Garg
 */
@Getter
public class PasswordResetConfirmationPage extends AbstractComponent {

    private WebDriver driver;
    public PasswordResetConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "td.reg_success")
    private WebElement resetPasswordSuccessMessage;

}
