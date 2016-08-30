package com.vitalsource.pages.BookShelf;

import com.vitalsource.lib.FileOperation;
import com.vitalsource.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.NoSuchElementException;

import static org.testng.Assert.assertEquals;

/**
 * Created by Alexis Espinoza on 7/19/16.
 */
public class SignInPage extends BasePage {
    public SignInPage(WebDriver driver,String browser, String browser_version, boolean navigate, String page, HashMap<String,Object> languageHashMap,boolean checkForLang){
        super (driver, browser, browser_version, navigate, page+"#/user/signin", languageHashMap, checkForLang,"Sign In with Language check = " + checkForLang);
    }
    public void clickOnMenuElement(String menuOption) {
        try {
            driver.findElement(By.partialLinkText(menuOption)).click();
        }  catch (NoSuchElementException e) {
            System.out.println("Element " +menuOption+ " Not Found");
        }
    }
    /**
     * This function authenticates to the vital source site from the bookshelf subdomain
     * If checkForLang = true then it will assert for the language text that should be defined in
     * the resource/lang folder
     * @param  user  username
     * @param  pass user's password
     */
    public void authenticate(String user, String pass) {
        if (checkForLang) {
            //Shop now
            By[] byLang = {By.cssSelector("#bookshelf > div > header > div"),
                    By.cssSelector("#bookshelf > div > header > div > ul > li.store-item"),
                    By.cssSelector("#bookshelf > div > header > div > ul > li.store-item > a"),
            };
            String textCheck = findNestedElements(byLang).getText();
            assertEquals(textCheck, languageHashMap.get(textCheck));
            //Support
            byLang    = new By[1];
            byLang[0] = By.cssSelector("#bookshelf > div > header > div > ul > li.support-item");
            textCheck = findNestedElements(byLang).getText();
            assertEquals(textCheck, languageHashMap.get(textCheck));
            //Welcome to
            byLang    = new By[4];
            byLang[0] = By.cssSelector("#bookshelf > div > div:nth-child(2) > div > div.account > div.account-content.signin");
            byLang[1] = By.cssSelector("#bookshelf > div > div:nth-child(2) > div > div.account > div.account-content.signin > h1.large");
            byLang[2] = By.cssSelector("#bookshelf > div > div:nth-child(2) > div > div.account > div.account-content.signin > h1.large > span");
            byLang[3] = By.cssSelector("#bookshelf > div > div:nth-child(2) > div > div.account > div.account-content.signin > h1.large > span > span:nth-child(1)");
            textCheck = findNestedElements(byLang).getText();
            assertEquals(textCheck, languageHashMap.get(textCheck));
            //Sign In button
           /* byLang    = new By[2];
            byLang[0] = By.cssSelector("#signin-form");
            byLang[1] = By.className("large-button");
            textCheck = findNestedElements(byLang).getText();
            assertEquals(textCheck, languageHashMap.get(textCheck));
            */
            //Forgot password
            byLang    = new By[1];
            byLang[0] = By.cssSelector("#signin-form > a");
            textCheck = findNestedElements(byLang).getText();
            assertEquals(textCheck, languageHashMap.get(textCheck));

            //New to VitalSource? Create an account.
            byLang    = new By[2];
            byLang[0] = By.cssSelector("#bookshelf > div > div:nth-child(2) > div > div.account > div.account-content.signin > div.subheading");
            byLang[1] = By.cssSelector("#bookshelf > div > div:nth-child(2) > div > div.account > div.account-content.signin > div.subheading > span");
            textCheck = findNestedElements(byLang).getText();
            assertEquals(textCheck, languageHashMap.get(textCheck));
        }
        By [] by = {By.cssSelector("#signin-form"), By.cssSelector("#signin-form > div.email-field"), By.cssSelector("#email-field") };
        findNestedElementsAndSendKeys(by, user);
        by = new By[2];
        by[0] = By.xpath("//*[@id=\"signin-form\"]/div[3]");
        by[1] = By.xpath("//*[@id=\"password-field\"]");
        findNestedElementsAndSendKeys(by,pass);
        by = new By[1];
        by[0] = By.className("large-button");
        findNestedElementsAndClick(by,true);
    }
    /**
     * This function tests that the overlay text for invalid email or pasword appears once
     * wrong credentials are used as input.
     */
    public void assertTextForInvalidEmailOrPass(String assertText) {
        By [] by = {By.cssSelector("#notification-container"), By.cssSelector("#notification-container > div")};
        assertEquals(findNestedElements(by).getText(), assertText);
    }

    /**
     * This function checks if the robot is activated. Selenium alone cannot deal with the robot options
     * so once the robot is activated, false should be returned
     * @return      true if the robot is activated or false if not
     */
    public boolean checkForRobot() {
        Boolean isPresent = driver.findElements(By.cssSelector("#g-recaptcha > div > div > iframe")).size() > 0;
        if(isPresent) {
            WebElement iframeMsg = driver.findElement(By.cssSelector("#g-recaptcha > div > div > iframe"));
            driver.switchTo().frame(iframeMsg);
            By [] by = {By.cssSelector("html"),
                    By.cssSelector("body"),
                    By.cssSelector("body > div.rc-anchor.rc-anchor-normal.rc-anchor-light"),
                    By.cssSelector("body > div.rc-anchor.rc-anchor-normal.rc-anchor-light > div.rc-anchor-content"),
                    By.cssSelector("body > div.rc-anchor.rc-anchor-normal.rc-anchor-light > div.rc-anchor-content > div:nth-child(1)"),
                    By.cssSelector("body > div.rc-anchor.rc-anchor-normal.rc-anchor-light > div.rc-anchor-content > div:nth-child(1) > div"),
                    By.cssSelector("body > div.rc-anchor.rc-anchor-normal.rc-anchor-light > div.rc-anchor-content > div:nth-child(1) > div > div"),
                    By.cssSelector("#recaptcha-anchor"),
                    By.cssSelector("#recaptcha-anchor > div.recaptcha-checkbox-checkmark")};
            findNestedElementsAndClick(by,true);
            return true;
        } else {
            return false;
        }
    }
    /**
     * This function chooses a language from the bottom main at login page.
     *  @param lang: any of the language found in the resource/lang folder.
     *             You must drop the '.txt' and use the name of the language only as param
     */
    public void checkChooseLanguage(String lang) throws InterruptedException{
        By [] by = {By.cssSelector("#bookshelf > div > div:nth-child(2) > div"), By.cssSelector("#bookshelf > div > div:nth-child(2) > div > span")
                    ,By.cssSelector("#bookshelf > div > div:nth-child(2) > div > span > button")};
        findNestedElementsAndClick(by,true);
        Thread.sleep(1000);
        by    = new By[4];
        HashMap <String, Object> languageOrder = FileOperation.readFileIntoHashMap(System.getProperty("user.dir") + "/src/test/resources/scenarios/languageorder.txt", 1);
       // languages.put();
        by[0] = By.cssSelector("#language-dropdown-menu");
        by[1] = By.cssSelector("#language-list");
        by[2] = By.cssSelector("#language-list > ul");
        System.out.println("lng "+lang);
        int langNumber = ((Integer)languageOrder.get(lang)).intValue();
        langNumber++;
        System.out.println("#language-list > ul > li:nth-child(" +langNumber + ")");
        by[3] = By.cssSelector("#language-list > ul > li:nth-child(" +langNumber + ")");
        findNestedElementsAndClick(by,true);
    }
    /**
     * This function clicks on the Shop Now upper menu option to jump out of the
     * bookshelf subdomin
     */
    public void signInVitalSource () {
        By[] by= {By.cssSelector("#bookshelf > div > header > div"),
                By.cssSelector("#bookshelf > div > header > div > ul > li.store-item"),
                By.cssSelector("#bookshelf > div > header > div > ul > li.store-item > a"),
        };
        findNestedElementsAndClick(by, true);
    }
}
