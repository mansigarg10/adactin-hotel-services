package com.mansi.adactin.pages;

import com.mansi.adactin.utils.AbstractComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains web elements of login page.
 *
 * @author Mansi Garg
 */
@Getter
public class LoginPage extends AbstractComponent {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id ="username")
    private WebElement userName;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(css = ".login_button")
    private WebElement loginButton;

    @FindBy(css = "td.login_register a")
    private WebElement registerLink;

    @FindBy(css = "div.login_forgot a")
    private WebElement forgotPasswordLink;

    public SearchHotelPage userLogin(String username, String pass) {
        userName.sendKeys(username);
        password.sendKeys(pass);
        loginButton.click();
        return new SearchHotelPage(driver);
    }

}
