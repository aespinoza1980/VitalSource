package com.vitalsource.tests;

import com.vitalsource.pages.BookShelf.BookPage;
import com.vitalsource.pages.BookShelf.MainPage;
import com.vitalsource.pages.BookShelf.SignInPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Alexis Espinoza on 8/26/16.
 */
public class AddDeleteHighLightTest extends BaseTest{
    private BookPage bookPage;
    private MainPage mainPage;
    @Parameters({"browser", "browser_version", "os", "os_version", "mobileBrowserName","mobilePlatform","mobileDevice","language","checkLang"})
    @BeforeTest
    public void setUpLocal(@Optional("firefox") String browser,@Optional("")String browser_version,@Optional("")String os,
                           @Optional("")String os_version,
                           @Optional("")String mobileBrowserName,
                           @Optional("")String mobilePlatform,
                           @Optional("")String mobileDevice,
                           @Optional("english") String language,
                           @Optional("false") Boolean checkLang)throws IOException {
        this.setUp(browser,browser_version,os,os_version,mobileBrowserName,mobilePlatform,mobileDevice,language,checkLang);
    }

    @Test
    public void signIn() throws InterruptedException{
        SignInPage signInPage = new SignInPage(driver,browser,browser_version, true, propertyValues[0],languageHashMap,checkLang);
        signInPage.authenticate(validUsername,validPassword);
        mainPage = new MainPage(driver,browser,browser_version, false, propertyValues[0],languageHashMap,checkLang);
        mainPage.tutorial();
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods = "signIn")
    public void addHighLight() throws InterruptedException {
        bookPage  = new BookPage(driver,browser,browser_version, true, bookLink,languageHashMap,checkLang);
        bookPage.addHighLight();
        checkHighLightsOnPage(1);
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods = "addHighLight")
    public void deleteHighLight() throws InterruptedException {
        bookPage  = new BookPage(driver,browser,browser_version, true, bookLink,languageHashMap,checkLang);
        bookPage.deleteHighLight();
        checkHighLightsOnPage(0);
        Thread.sleep(1000);
    }
    private void checkHighLightsOnPage(int numberOfHighLights) throws InterruptedException {
        mainPage = new MainPage(driver, browser, browser_version, true, propertyValues[0],languageHashMap,checkLang);
        Thread.sleep(2000);
        mainPage.checkHighlights(numberOfHighLights);
    }
}
