package com.vitalsource.tests;

import com.vitalsource.pages.BookShelf.SignInPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Alexis Espinoza on 8/25/16.
 */
public class UnSuccessfulLogInTest extends BaseTest {
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
    public void UnSuccessfulLogIn() throws InterruptedException {
        SignInPage signInPage = new SignInPage(driver, browser, browser_version, true, propertyValues[0],languageHashMap,checkLang);
        if(!signInPage.checkForRobot()) {
            signInPage.authenticate(invalidUsername, validPassword);
            if(!signInPage.checkForRobot()) {
                signInPage.assertTextForInvalidEmailOrPass(invalidEmailPass);
                Thread.sleep(2000);
                signInPage.authenticate(validUsername,invalidPassword);
                if(!signInPage.checkForRobot()) {
                    signInPage.assertTextForInvalidEmailOrPass(invalidEmailPass);
                } else {
                    System.out.println(cantFoolCaptchaYet);
                }
                Thread.sleep(2000);
            } else {
                System.out.println(cantFoolCaptchaYet);
                Thread.sleep(2000);
            }
        } else {
            System.out.println(cantFoolCaptchaYet);
            Thread.sleep(2000);
        }
        close();
    }
}
