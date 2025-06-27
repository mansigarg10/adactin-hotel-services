package com.mansi.adactin.pages;

import com.mansi.adactin.utils.AbstractComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains web elements of Booking Confirmation page.
 *
 * @author Mansi Garg
 */
@Getter
public class BookingConfirmationPage extends AbstractComponent {

    private WebDriver driver;
    public BookingConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "my_itinerary")
    private WebElement myItineraryButton;

}
