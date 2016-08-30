package com.vitalsource.tests;


import com.vitalsource.pages.BookShelf.MainPage;
import com.vitalsource.pages.BookShelf.SignInPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Alexis Espinoza on 7/19/16.
 */
public class LoginLogOutBookShelfTest extends BaseTest {
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
        SignInPage signInPage = new SignInPage(driver, browser, browser_version, true, propertyValues[0],languageHashMap,checkLang);
        if(!language.equals("english")) {
            signInPage.checkChooseLanguage(language);
            Thread.sleep(2000);
        }
        signInPage.authenticate(validUsername,validPassword);
        mainPage = new MainPage(driver, browser, browser_version, false, propertyValues[0],languageHashMap,checkLang);
        mainPage.tutorial();
        Thread.sleep(1000);
    }
    @Test(dependsOnMethods = "signIn")
    public void LogOut() throws InterruptedException {
        mainPage.logOut(true);
        Thread.sleep(2000);
        close();
    }
}
