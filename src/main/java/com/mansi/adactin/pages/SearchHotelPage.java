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
    private final String requiredHotel = "Hotel Sunshine";
    private final String requiredRoomType = "Super Deluxe";
    private final String requiredNumberOfRooms = "6 - Six";
    private final String locationName = "London";
    private final String checkInDateValue = "20/11/2025";
    private final String checkOutDateValue = "10/12/2025";
    private final String adultPerRoom = "4 - Four";
    private final String childPerRoom = "1 - One";

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
        selectElementByText(location, locationName);
        hotels.click();
        getTheRequiredElementSelected(hotelNames, requiredHotel);
        roomType.click();
        getTheRequiredElementSelected(roomTypeOptions, requiredRoomType);
        roomNos.click();
        getTheRequiredElementSelected(roomNosOptions, requiredNumberOfRooms);
        waitForElementToBeClickable(checkInDate);
        checkInDate.clear();
        checkInDate.sendKeys(checkInDateValue);
        checkOutDate.clear();
        checkOutDate.sendKeys(checkOutDateValue);
        adultRoom.click();
        selectElementByText(adultRoom, adultPerRoom);
        childRoom.click();
        selectElementByText(childRoom, childPerRoom);
        searchButton.click();
        return new SelectHotelPage(driver);
    }

}
