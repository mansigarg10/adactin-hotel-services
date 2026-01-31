package com.mansi.adactin.utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class CaptchaReader {


    public static String readCaptchaImage(WebElement imageElement, String path) throws IOException, TesseractException {


        File src = imageElement.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(path));

        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath(
                System.getProperty("user.dir") + "/tessdata"
        );
        tesseract.setLanguage("eng");
        tesseract.setPageSegMode(7);
        tesseract.setOcrEngineMode(1);

        String text = tesseract.doOCR(new File(path));

        text = text.replaceAll("[^a-zA-Z0-9]", "").trim();
        return text;
    }





}
