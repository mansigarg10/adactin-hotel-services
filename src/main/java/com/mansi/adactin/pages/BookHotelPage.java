package com.mansi.adactin.pages;

import com.mansi.adactin.utils.AbstractComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * This class contains web elements of Book hotel page.
 *
 * @author Mansi Garg
 */
@Getter
public class BookHotelPage extends AbstractComponent {

    private WebDriver driver;

    public BookHotelPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first_name")
    private WebElement firstName;

    @FindBy(id = "last_name")
    private WebElement lastName;

    @FindBy(id = "address")
    private WebElement address;

    @FindBy(id = "cc_num")
    private WebElement creditCardNumber;

    @FindBy(xpath = "//select[@id='cc_type']/option")
    private List<WebElement> creditCardType;

    @FindBy(id = "cc_exp_month")
    private WebElement creditCardExpMonth;

    @FindBy(id = "cc_exp_year")
    private WebElement creditCardExpYear;

    @FindBy(id = "cc_cvv")
    private WebElement creditCardCVV;

    @FindBy(id = "book_now")
    private WebElement bookNowButton;

    public BookingConfirmationPage provideBookingDetails(String name, String surname, String currentAddress, String cardNumber, String cardBrand, String cardExpiryMonth, String cardExpiryYear, String cvv) {
        firstName.sendKeys(name);
        lastName.sendKeys(surname);
        address.sendKeys(currentAddress);
        scrollDownWindow();
        creditCardNumber.sendKeys(cardNumber);
        for (WebElement type : creditCardType) {
            String cardType = type.getText();
            if (cardType.equalsIgnoreCase(cardBrand)) {
                type.click();
                break;
            }
        }
        creditCardExpMonth.sendKeys(cardExpiryMonth);
        creditCardExpYear.sendKeys(cardExpiryYear);
        creditCardCVV.sendKeys(cvv);
        bookNowButton.click();
        BookingConfirmationPage bookingConfirmationPage = new BookingConfirmationPage(driver);
        waitForElementToBeClickable(bookingConfirmationPage.getMyItineraryButton());
        return bookingConfirmationPage;
    }

}
