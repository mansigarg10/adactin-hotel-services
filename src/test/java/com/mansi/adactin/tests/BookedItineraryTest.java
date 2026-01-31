package com.mansi.adactin.tests;

import com.mansi.adactin.listeners.BaseTest;
import com.mansi.adactin.pages.BookedItineraryPage;
import com.mansi.adactin.utils.AdactinTestConstants;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * This class validates the cancel booking functionality.
 *
 * @author Mansi Garg
 */
public class BookedItineraryTest extends BaseTest {

    @Test(priority = 3)
    public void cancelHotelBooking() throws IOException {
        driver = initializeBrowser(AdactinTestConstants.HOTEL_WEBSITE_URL);
        FileInputStream fileInputStream = new FileInputStream(propetyFile);
        prop.load(fileInputStream);
        BookedItineraryPage bookedItineraryPage = new BookedItineraryPage(driver);
        bookedItineraryPage.cancelBooking(prop.getProperty("username"), prop.getProperty("password"));
        driver.switchTo().alert().accept();
    }

}
