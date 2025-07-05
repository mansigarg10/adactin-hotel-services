package com.mansi.adactin.tests;

import com.mansi.adactin.listeners.BaseTest;
import com.mansi.adactin.pages.*;
import com.mansi.adactin.utils.AdactinConstants;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * This class validates the room booking functionality.
 *
 * @author Mansi Garg
 */
public class RoomBookingTest extends BaseTest {

    private final String LOGIN_FILE = System.getProperty("user.dir") + AdactinConstants.LOGIN_JSON_PATH;
    private final String BOOKING_DETAILS_FILE = System.getProperty("user.dir") + AdactinConstants.BOOKING_JSON_PATH;

    @Test(dataProvider = "getData", priority = 2)
    public  void searchHotel(HashMap<String,String> input, HashMap<String,String> input1) throws InterruptedException, IOException {
        driver = initializeBrowser();
        LoginPage loginPage = new LoginPage(driver);
        SearchHotelPage searchHotelPage = loginPage.userLogin(input.get("username"), input.get("password"));
        SelectHotelPage selectHotelPage = searchHotelPage.searchHotel();
        BookHotelPage bookHotelPage = selectHotelPage.selectTheHotel();
        BookingConfirmationPage bookingConfirmationPage = bookHotelPage.provideBookingDetails(input1.get("k_name"),
                input1.get("k_surname"), input1.get("k_currentAddress"), input1.get("k_cardNumber"),
                input1.get("k_cardBrand"), input1.get("k_cardExpiryMonth"),
                input1.get("k_cardExpiryYear"), input1.get("k_cardCVV"));
        bookingConfirmationPage.getMyItineraryButton().click();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(LOGIN_FILE);
        List<HashMap<String, String>> data1 = getJsonData(BOOKING_DETAILS_FILE);
        return new Object[][] {
                { data.get(0), data1.get(0) }
        };
    }

}
