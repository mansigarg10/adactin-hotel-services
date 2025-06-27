package com.mansi.adactin.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.time.Duration.ofSeconds;

/**
 * This class contains common methods.
 *
 * @author Mansi Garg
 */
public class AbstractComponent {

    private final WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollDownWindow() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,500)");
    }

    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void selectElementByText(WebElement element,String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void getTheRequiredElementSelected(List<WebElement> elements,String requiredText) {
        for (WebElement element :elements) {
            String elementText = element.getText();
            if (elementText.equalsIgnoreCase(requiredText)) {
                element.click();
            }
        }
    }

}
