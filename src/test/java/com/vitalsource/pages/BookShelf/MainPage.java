package com.vitalsource.pages.BookShelf;

import com.vitalsource.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alexis Espinoza on 8/25/16.
 */
public class MainPage extends BasePage {
    public MainPage(WebDriver driver, String  browser, String browser_version, boolean navigate, String page, HashMap<String,Object> languageHashMap,boolean checkForLang) {
        super(driver, browser, browser_version, navigate, page + "#/", languageHashMap,checkForLang, "Main Page with Language check = " + checkForLang);
    }
    /**
     * This function clicks on the user icon from the top menu, this opens a option overlay and then clicks on logout
     * leftmost option on the bottom of the panel
     */
    public void logOut(boolean doubleClick) {

        By[] by = { By.cssSelector("#bookshelf > div > header > div > span"), By.cssSelector("#bookshelf > div > header > div > span > ul"),
                By.cssSelector("#user-dropdown-dom-1"), By.cssSelector("#user-dropdown-dom-1 > button")};
        WebElement webElement = findNestedElements(by);
        webElement.click();
        if(doubleClick) { //performs double click when called upon
            webElement.click();
        }

        by = new By[2];
        by[0] = By.cssSelector("#user-dropdown-menu-1 > div.account-footer");
        by[1] = By.cssSelector("#user-dropdown-menu-1 > div.account-footer > button");
        findNestedElementsAndClick(by, true);
    }
    /**
     * Opens or closes the collection of books available from the leftmost panel
     */
    public void openCloseCollection() {
        By[] by = {By.cssSelector("#library-filter > div.left > ul.icon-container.filter-list"),
                By.cssSelector("#library-filter > div.left > ul.icon-container.filter-list > button")};
        findNestedElementsAndClick(by, true);
    }
    /**
     * Goes thorugh the 5 pages in the tutorial.
     */
    public void tutorial() throws InterruptedException {
        Boolean isPresent = driver.findElements(By.cssSelector("#bookshelf > div > div.modal.onboarding-modal.onboarding.fullscreen.undefined > div")).size() > 0;
        if(isPresent) {
            Thread.sleep(1000);
            By[] by = {By.cssSelector("#bookshelf > div > div.modal.onboarding-modal.onboarding.fullscreen.undefined > div > div"),
                   By.cssSelector("#bookshelf > div > div.modal.onboarding-modal.onboarding.fullscreen.undefined > div > div > div"),
                   By.cssSelector("#bookshelf > div > div.modal.onboarding-modal.onboarding.fullscreen.undefined > div > div > div > div.onboarding-footer"),
                   By.cssSelector("#bookshelf > div > div.modal.onboarding-modal.onboarding.fullscreen.undefined > div > div > div > div.onboarding-footer > div"),
                   By.cssSelector("#bookshelf > div > div.modal.onboarding-modal.onboarding.fullscreen.undefined > div > div > div > div.onboarding-footer > div > button.noButton.next-button.nav-button")
            };
            findNestedElementsAndClick(by, true);
            by    = new By[1];
            by[0] = By.cssSelector("#bookshelf > div > div.modal.onboarding-modal.onboarding.fullscreen.undefined > div > div > div > div.onboarding-footer > div > button.noButton.next-button.nav-button");
            int i = 0;
            while(i < 3) {
                Thread.sleep(1000);
                findNestedElementsAndClick(by, true);
                i++;
            }
        }
    }
    /**
     * Selects the all books, books I've read option
     */
    public void subMenuCheck() throws InterruptedException {
        int i = 2;
        while (i > 0) {
            By [] by = {By.cssSelector("#library-filter > div.left > ul.dropdown-container.filter-list"),
                    By.cssSelector("#library-filter > div.left > ul.dropdown-container.filter-list > button"),
                    By.cssSelector("#library-filter > div.left > ul.dropdown-container.filter-list > button > li")};
            WebElement webElement = findNestedElements(by);
            assertEquals(webElement.getText(), languageHashMap.get(webElement.getText()));
            webElement.click();
            Thread.sleep(1000);
            by    = new By[2];
            by[0] = By.cssSelector("#library-filter-dropdown > ul > li:nth-child(" + i + ")");
            by[1] = By.cssSelector("#library-filter-dropdown > ul > li:nth-child(" + i + ") > button");
            findNestedElementsAndClick(by, true);
            i--;
        }
    }
    /**
     * This function opens the redeem option modal window and inserts a redeem code in the input
     * text
     */
    public void redeemWrongCode(String redeemCode) throws InterruptedException{
        /*This won't actually open the collection from here. The whole page needs to be loaded
        and one whay to make it avaliable is to attempt to open teh collection. If you want to perfomr this
        this activities, uncomment the opencollection lines from the test */
        openCloseCollection();
        By [] by = {By.cssSelector("#bookshelf"),
                    By.cssSelector("#bookshelf > div"),
                    By.cssSelector("#bookshelf > div > header"),
                    By.cssSelector("#bookshelf > div > header > div"),
                    By.cssSelector("#bookshelf > div > header > div > span"),
                    By.cssSelector("#bookshelf > div > header > div > span > ul"),
                    By.cssSelector("#bookshelf > div > header > div > span > ul > li.redeem-item")
                   };
        WebElement webElement = findNestedElements(by);
        webElement.click();
        Thread.sleep(1000);
        //Modal window
        by    = new By[6];
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        by[0] = By.cssSelector("#bookshelf > div > div.modal.undefined");
        by[1] = By.cssSelector("#bookshelf > div > div.modal.undefined > div");
        by[2] = By.cssSelector("#bookshelf > div > div.modal.undefined > div > div.modal-content.with-buttons");
        by[3] = By.cssSelector("#bookshelf > div > div.modal.undefined > div > div.modal-content.with-buttons > div");
        by[4] = By.cssSelector("#bookshelf > div > div.modal.undefined > div > div.modal-content.with-buttons > div > form");
        by[5] = By.id("redeemCode");
        findNestedElementsAndSendKeys(by,redeemCode);
        //Redeem Button
        Thread.sleep(1000);
        by    = new By[2];
        by[0] = By.cssSelector("#bookshelf > div > div.modal.undefined > div > div.modal-buttons");
        by[1] = By.cssSelector("#bookshelf > div > div.modal.undefined > div > div.modal-buttons > button:nth-child(2)");
        findNestedElementsAndClick(by, true);
        //Error message
        webElement = driver.findElement(By.cssSelector("#notification-container > div"));
        assertEquals(webElement.getText(), languageHashMap.get(webElement.getText()));
        //Close button
        by    = new By[2];
        by[0] = By.cssSelector("#bookshelf > div > div.modal.undefined > div");
        by[1] = By.cssSelector("#bookshelf > div > div.modal.undefined > div > button");
        findNestedElementsAndClick(by, true);
    }
    /**
     * Checks the number of highlight per book. Only tested on the first book.
     */
    public void checkHighlights(int numberOfHighlights) {
        By [] by = {
                By.cssSelector("#bookshelf"),
                By.cssSelector("#bookshelf > div"),
                By.cssSelector("#library"),
                By.cssSelector("#book-list"),
                By.cssSelector("#library-card-VSMTEST-SALES-DEMO-EPUB-0"),
                By.cssSelector("#library-card-VSMTEST-SALES-DEMO-EPUB-0 > div") ,
                By.cssSelector("#library-card-VSMTEST-SALES-DEMO-EPUB-0 > div > ul"),
                By.cssSelector("#library-card-VSMTEST-SALES-DEMO-EPUB-0 > div > ul > li.book-highlights")
        };
        assertEquals(findNestedElements(by).getText(), String.valueOf(numberOfHighlights));
    }

    public void manageHighLighter() throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement webElement1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#bookshelf")));
        WebElement webElement2 = webElement1.findElement(By.cssSelector("#bookshelf > div"));
        WebElement webElement3 = webElement2.findElement(By.cssSelector("#bookshelf > div > header"));
        WebElement webElement4 = webElement3.findElement(By.cssSelector("#bookshelf > div > header > div"));
        WebElement webElement5 = webElement4.findElement(By.cssSelector("#bookshelf > div > header > div > span"));
        WebElement webElement6 = webElement5.findElement(By.cssSelector("#bookshelf > div > header > div > span > ul"));
        WebElement webElement7 = webElement6.findElement(By.cssSelector("#user-dropdown-dom"));
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement7).perform();
       // actions.moveToElement(webElement7).click().perform();
        webElement7.click();
        Thread.sleep(2000);
        webElement1 = driver.findElement(By.cssSelector("#user-dropdown-menu > ul"));
        webElement2 = webElement1.findElement(By.cssSelector("#user-dropdown-menu > ul > li:nth-child(2)"));
        webElement2.click();
        //actions.moveToElement(webElement2).click();
        Thread.sleep(1000);
    }
}
