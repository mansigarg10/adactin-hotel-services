package com.mansi.adactin.pages;

import com.mansi.adactin.utils.AbstractComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * This class contains web elements of search hotel page.
 *
 * @author Mansi Garg
 */
@Getter
public class SearchHotelPage extends AbstractComponent {

    private final WebDriver driver;

    private final String REQUIRED_HOTEL = "Hotel Sunshine";
    private final String REQUIRED_ROOM_TYPE = "Super Deluxe";
    private final String REQUIRED_NUMBER_OF_ROOMS = "6 - Six";
    private final String LOCATION_NAME = "London";
    private final String CHECK_IN_DATE = "20/11/2025";
    private final String CHECK_OUT_DATE = "10/12/2025";
    private final String ADULTS_PER_ROOM = "4 - Four";
    private final String CHILD_PER_ROOM = "1 - One";

    public SearchHotelPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "location")
    private WebElement location;

    @FindBy(id = "hotels")
    private WebElement hotels;

    @FindBy(css = "select#hotels option")
    private List<WebElement> hotelNames;

    @FindBy(id = "room_type")
    private WebElement roomType;

    @FindBy(css = "select#room_type option")
    private List<WebElement> roomTypeOptions;

    @FindBy(id = "room_nos")
    private WebElement roomNos;

    @FindBy(css ="select#room_nos option")
    private List<WebElement> roomNosOptions;

    @FindBy(css = "input#datepick_in")
    private WebElement checkInDate;

    @FindBy(css ="input#datepick_out")
    private WebElement checkOutDate;

    @FindBy(id = "adult_room")
    private WebElement adultRoom;

    @FindBy(id = "child_room")
    private WebElement childRoom;

    @FindBy(id = "Submit")
    private WebElement searchButton;

    @FindBy(css = "td.welcome_menu a:nth-child(3)")
    private WebElement BookedItinerary;

    @FindBy(css = "td.welcome_menu a:nth-child(5)")
    private WebElement Logout;

    @FindBy(css = "td.welcome_menu a:nth-child(4)")
    private WebElement ChangePassword;

    public SelectHotelPage searchHotel() throws InterruptedException {
        location.click();
        selectElementByText(location, LOCATION_NAME);
        hotels.click();
        getTheRequiredElementSelected(hotelNames, REQUIRED_HOTEL);
        roomType.click();
        getTheRequiredElementSelected(roomTypeOptions, REQUIRED_ROOM_TYPE);
        roomNos.click();
        getTheRequiredElementSelected(roomNosOptions, REQUIRED_NUMBER_OF_ROOMS);
        waitForElementToBeClickable(checkInDate);
        checkInDate.clear();
        checkInDate.sendKeys(CHECK_IN_DATE);
        checkOutDate.clear();
        checkOutDate.sendKeys(CHECK_OUT_DATE);
        adultRoom.click();
        selectElementByText(adultRoom, ADULTS_PER_ROOM);
        childRoom.click();
        selectElementByText(childRoom, CHILD_PER_ROOM);
        searchButton.click();
        return new SelectHotelPage(driver);
    }

}
