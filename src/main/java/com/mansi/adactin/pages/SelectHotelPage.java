package com.mansi.adactin.pages;

import com.mansi.adactin.utils.AbstractComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains web elements of select hotel page.
 *
 * @author Mansi Garg
 */
@Getter
public class SelectHotelPage extends AbstractComponent {

    private WebDriver driver;
    public SelectHotelPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath ="//input[@id='radiobutton_0']")
    private WebElement radioButton;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public BookHotelPage selectTheHotel() {
        radioButton.click();
        continueButton.click();
        return new BookHotelPage(driver);
    }

}
