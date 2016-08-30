package com.vitalsource.pages.VitalSource;

import com.vitalsource.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

/**
 * Created by Alexis Espinoza on 8/28/16.
 */
public class StudentETextBooksPage extends BasePage {
    public StudentETextBooksPage(WebDriver driver,String browser, String browser_version, boolean navigate, String page, HashMap<String, Object> languageHashMap, boolean checkForLang){
        super (driver,browser, browser_version, navigate, page, languageHashMap, checkForLang,"Vital Source User Signed In with Language check = " + checkForLang);
    }
    /**
     * This function clicks on the user icon from the vitalsource page, an overlay sub menu appears and the clicks
     * on log out
     */
    public void logOut() throws InterruptedException{
        By[] by = {By.cssSelector("body > div.mobile-side-menu__content-wrapper.mobile-side-menu--hidden > header > nav > div.header__main-nav > div > div"),
                   By.cssSelector("body > div.mobile-side-menu__content-wrapper.mobile-side-menu--hidden > header > nav > div.header__main-nav > div > div > div"),
                   By.cssSelector("body > div.mobile-side-menu__content-wrapper.mobile-side-menu--hidden > header > nav > div.header__main-nav > div > div > div > ul"),
                   By.cssSelector("body > div.mobile-side-menu__content-wrapper.mobile-side-menu--hidden > header > nav > div.header__main-nav > div > div > div > ul > li.dropdown.account-center"),
                   By.cssSelector("#account-center")
                  };
        findNestedElementsAndClick(by,true);
        Thread.sleep(1000);
        by    = new By[4];
        by[0] = By.cssSelector("body > div.mobile-side-menu__content-wrapper.mobile-side-menu--hidden > header > nav > div.header__main-nav > div > div > div > ul > li.dropdown.account-center.open > div");
        by[1] = By.cssSelector("body > div.mobile-side-menu__content-wrapper.mobile-side-menu--hidden > header > nav > div.header__main-nav > div > div > div > ul > li.dropdown.account-center.open > div > div.account-center__action");
        by[2] = By.cssSelector("body > div.mobile-side-menu__content-wrapper.mobile-side-menu--hidden > header > nav > div.header__main-nav > div > div > div > ul > li.dropdown.account-center.open > div > div.account-center__action > form");
        by[3] = By.cssSelector("body > div.mobile-side-menu__content-wrapper.mobile-side-menu--hidden > header > nav > div.header__main-nav > div > div > div > ul > li.dropdown.account-center.open > div > div.account-center__action > form > input.vs-btn--xsmall.vs-btn--blue.qa-sign-out-btn");
        findNestedElementsAndClick(by,true);
        //Make sure we have signed out
        Thread.sleep(1000);
        checkSignedOut();
    }
    /**
     * This function asserts that the test has signed out from the vitalsource engine
     */
    private void checkSignedOut() {
        boolean signedOut =false;
        By[] by = {By.cssSelector("#flash_messages"),
                By.cssSelector("#flash_messages > div"),
                By.cssSelector("#flash_messages > div > div"),
                By.cssSelector("#flash_messages > div > div > div"),
                By.cssSelector("#flash_messages > div > div > div > div")
        };
        WebElement webElement = findNestedElements(by);
        String [] splitString = webElement.getText().split("\\.");
        assertEquals(splitString[0], languageHashMap.get(splitString[0]));
    }
}
