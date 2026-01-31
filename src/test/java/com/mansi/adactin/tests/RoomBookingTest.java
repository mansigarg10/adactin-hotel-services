package com.mansi.adactin.tests;

import com.mansi.adactin.listeners.BaseTest;
import com.mansi.adactin.pages.*;
import com.mansi.adactin.utils.AdactinConstants;
import com.mansi.adactin.utils.AdactinTestConstants;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * This class validates the room booking functionality.
 *
 * @author Mansi Garg
 */
public class RoomBookingTest extends BaseTest {

    private final String BOOKING_DETAILS_FILE = System.getProperty("user.dir") + AdactinConstants.BOOKING_JSON_PATH;

    @Test(dataProvider = "getData", priority = 2)
    public  void searchHotel(HashMap<String,String> input) throws InterruptedException, IOException {
        driver = initializeBrowser(AdactinTestConstants.HOTEL_WEBSITE_URL);
        LoginPage loginPage = new LoginPage(driver);
        FileInputStream fileInputStream = new FileInputStream(propetyFile);
        prop.load(fileInputStream);
        SearchHotelPage searchHotelPage = loginPage.userLogin(prop.getProperty("username"), prop.getProperty("password")); ;
        SelectHotelPage selectHotelPage = searchHotelPage.searchHotel();
        BookHotelPage bookHotelPage = selectHotelPage.selectTheHotel();
        BookingConfirmationPage bookingConfirmationPage = bookHotelPage.provideBookingDetails(input.get("k_name"),
                input.get("k_surname"), input.get("k_currentAddress"), input.get("k_cardNumber"),
                input.get("k_cardBrand"), input.get("k_cardExpiryMonth"),
                input.get("k_cardExpiryYear"), input.get("k_cardCVV"));
        bookingConfirmationPage.getMyItineraryButton().click();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data1 = getJsonData(BOOKING_DETAILS_FILE);
        return new Object[][] {{data1.get(0) }
        };
    }

}
