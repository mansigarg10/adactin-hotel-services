package com.mansi.adactin.tests;

import com.mansi.adactin.listeners.BaseTest;
import com.mansi.adactin.pages.BookedItineraryPage;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * This class validates the cancel booking functionality.
 *
 * @author Mansi Garg
 */
public class BookedItineraryTest extends BaseTest {

    @Test(priority = 3)
    public void cancelHotelBooking() throws IOException {
        driver = initializeBrowser();
        BookedItineraryPage bookedItineraryPage = new BookedItineraryPage(driver);
        bookedItineraryPage.cancelBooking();
        driver.switchTo().alert().accept();
    }

}
