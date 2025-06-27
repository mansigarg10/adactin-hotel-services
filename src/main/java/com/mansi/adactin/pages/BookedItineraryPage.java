package com.mansi.adactin.pages;

import com.mansi.adactin.utils.AbstractComponent;
import com.mansi.adactin.utils.AdactinConstants;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains web elements of Book Itinerary page.
 *
 * @author Mansi Garg
 */
@Getter
public class BookedItineraryPage extends AbstractComponent {

    private WebDriver driver;
    private SearchHotelPage searchHotelPage;
    public BookedItineraryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input#check_all")
    private WebElement selectAllCheckbox;

    @FindBy(css = "input.reg_button:first-child")
    private WebElement cancelButton;
    public void cancelBooking() {
        LoginPage loginPage = new LoginPage(driver);
        searchHotelPage = loginPage.userLogin(AdactinConstants.USER_NAME, AdactinConstants.PASSWORD);
        searchHotelPage.getBookedItinerary().click();
        selectAllCheckbox.click();
        cancelButton.click();
    }

}
