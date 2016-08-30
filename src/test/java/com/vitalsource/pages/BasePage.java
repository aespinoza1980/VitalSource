package com.vitalsource.pages;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

/**
 * Created by Alexis Espinoza on 7/5/16.
 */
public class BasePage {
    protected WebDriver driver;
    protected HashMap<String,Object> languageHashMap;
    protected Boolean checkForLang;
    protected String browser;
    protected String browser_version;
    private static int invalidImageCount;
    WebDriverWait wait;

    /**
     * Empty constructor
     */
    public BasePage() { }
    /**
     * constructor
     * @param  driver  contains the driver of the browser set in the BaseTest
     * @param  browser  browser name
     * @param  browser_version  browser version
     * @param  navigate  boolean var. if set to true then page reloads.
     * @param  mainUrl the URL to go to.
     * @param  languageHashMap HashMap Array containing the language in which the test is being executed.
     * @param  checkForLang    boolean var. if set to true then language check is enforced.
     * @param message String option for message.
     */
    public BasePage(WebDriver driver,String browser, String browser_version, boolean navigate, String mainUrl, HashMap<String,Object> languageHashMap, boolean checkForLang,String message) {
        this.driver                = driver;
        this.languageHashMap       = languageHashMap;
        this.checkForLang          = checkForLang;
        this.browser               = browser;
        this.browser_version       = browser_version;
        if(navigate) {
            this.driver.navigate().to(mainUrl);
        }
        loadPage (mainUrl);
        assertEquals(mainUrl, driver.getCurrentUrl());
        System.out.println(message);

    }
    /**
     * This functions waits until a page is loaded.
     * @param link the URL to load
     */
    protected void loadPage (String link ) {
        boolean pageLoaded = false;
        while (!pageLoaded) {
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            try {
                assertEquals(link, driver.getCurrentUrl());
                pageLoaded = true;
            }
            catch (AssertionError  e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("Could not load "+link+ ". I will try again");
            }
        }
    }
    /**
     * This function creates a random word
     * @param length the length of the string to generate
     * @return  a String with random words
     */
    protected  String randomWord(int length) {
        Random random = new Random();
        StringBuilder word = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            word.append((char)('a' + random.nextInt(26)));
        }
        return word.toString();
    }

    /**
     * This function finds a nested web element and performs an implicit wait on each element it is traversing
     * @param by  array of By elements
     * @return  a nested WebElement
     */
    protected WebElement findNestedElementsWithPresenceEnforced(By [] by) {
        WebElement webElement;
        WebElement webElement1;
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(by[0]));
        webElement  = driver.findElement(by[0]);
        for (int i = 1; i < by.length; i++) {
            wait.until(ExpectedConditions.presenceOfElementLocated(by[i]));
            webElement  = driver.findElement(by[i]);
            webElement1 = webElement.findElement(by[i]);
            webElement  = webElement1;
        }
        return webElement;
    }

    /**
     * This function finds a nested web element
     * @param by  array of By elements
     * @return  a nested WebElement
     */
    protected WebElement findNestedElements(By [] by) {
        WebElement webElement;
        WebElement webElement1;
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(by[0]));
        webElement  = driver.findElement(by[0]);
        for (int i = 1; i < by.length; i++) {
            webElement1 = webElement.findElement(by[i]);
            webElement  = webElement1;
        }
        return webElement;
    }
    /**
     * This function finds a nested web element and clicks on it
     * @param by  array of By elements
     * @param click if set to true then the element found is clicked upon
     */
    protected void findNestedElementsAndClick(By [] by, boolean click) {
        if ((by.length > 0) && (click)) {
            findNestedElements(by).click();
        } else if ((by.length > 0) && (!click)) {
            findNestedElements(by);
        } else {
            System.out.println("Unable to click. Wrong params. Could not find elements.");
        }
    }

    /**
     * This function finds a nested web input element and sends the string to it.
     * @param by  array of By elements
     * @param keys the string to send to the inout form
     */
    protected void findNestedElementsAndSendKeys (By [] by, String keys) {
        WebElement webElement;
        if(by.length > 0) {
            webElement = findNestedElements(by);
            webElement.click();
            webElement.clear();
            webElement.sendKeys(keys);
        } else {
            System.out.println("Unable to send keys. Wrong params. Could not find elements");
        }
    }
    /**
     * This function scrolls down a page
     * @param pos  position down the page teh scroller must go down to
     */

    protected void scrollDown (int pos) throws  AWTException, InterruptedException{
        Robot robot = new Robot();
        int i = 0;
        while(i < pos) {
            robot.keyPress(KeyEvent.VK_PAGE_DOWN);
            robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
            i++;
            Thread.sleep(1000);
        }
    }


}