package com.vitalsource.pages.VitalSource;

import com.vitalsource.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

/**
 * Created by Alexis Espinoza on 8/28/16.
 */
public class VitalSourceSignInPage extends BasePage{
    private String mainPage;
    public VitalSourceSignInPage(WebDriver driver,String browser, String browser_version, boolean navigate, String mainPage, String subPage, HashMap<String,Object> languageHashMap,boolean checkForLang){
        super (driver,browser, browser_version, navigate, mainPage+subPage, languageHashMap, checkForLang,"Vital Source Sign In with Language check = " + checkForLang);
        this.mainPage = mainPage;
    }

    /**
     * This function clicks on the sign in menu option on the top bar of the vital source site
     */
    public void clickOnSignIn() {
        By[] by = {By.cssSelector("body > div.mobile-side-menu__content-wrapper.mobile-side-menu--hidden > header > nav > div.header__main-nav > div > div > div"),
                    By.cssSelector("body > div.mobile-side-menu__content-wrapper.mobile-side-menu--hidden > header > nav > div.header__main-nav > div > div > div > ul"),
                By.cssSelector("body > div.mobile-side-menu__content-wrapper.mobile-side-menu--hidden > header > nav > div.header__main-nav > div > div > div > ul > li:nth-child(4)"),
                By.cssSelector("body > div.mobile-side-menu__content-wrapper.mobile-side-menu--hidden > header > nav > div.header__main-nav > div > div > div > ul > li:nth-child(4) > a")};
        findNestedElementsAndClick(by,true);

    }
    /**
     * This function authenticates to the vital source site
     * @param  username  username
     * @param  pass username's password
     */
    public void signIn(String username, String pass) throws InterruptedException{
        assertEquals(mainPage+"login", driver.getCurrentUrl());
        By[] by = {By.cssSelector("#content > div > div > div"),
                By.cssSelector("#content > div > div > div > div"),
                By.cssSelector("#new_session"),
                By.cssSelector("#new_session > div.form-group.email.required.session_email.hide-help-block"),
                By.cssSelector("#session_email")};
        findNestedElementsAndSendKeys(by,username);
        Thread.sleep(500);
        by    = new By[2];
        by[0] = By.cssSelector("#new_session > div.form-group.password.required.session_password.hide-help-block");
        by[1] = By.cssSelector("#session_password");
        findNestedElementsAndSendKeys(by,pass);
        Thread.sleep(500);
        by    = new By[1];
        by[0] = By.cssSelector("#new_session > input.vs-btn.vs-btn--blue.btn-block.u-push--top");
        findNestedElementsAndClick(by,true);
    }
}
