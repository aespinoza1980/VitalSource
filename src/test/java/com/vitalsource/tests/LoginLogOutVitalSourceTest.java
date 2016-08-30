package com.vitalsource.tests;

import com.vitalsource.pages.BookShelf.SignInPage;
import com.vitalsource.pages.VitalSource.StudentETextBooksPage;
import com.vitalsource.pages.VitalSource.VitalSourceSignInPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Alexis Espinoza on 8/28/16.
 */
public class LoginLogOutVitalSourceTest extends BaseTest{
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
        String oldTab = driver.getWindowHandle();
        signInPage.signInVitalSource();
        Thread.sleep(20000);
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(oldTab);
        // change focus to new tab
        driver.switchTo().window(newTab.get(0));
        // change focus to new tab
        VitalSourceSignInPage vitalSourceSignInPage = new VitalSourceSignInPage(driver, browser, browser_version, true, propertyValues[9],"student-etextbooks?utm_source=bookshelfonline&utm_medium=bookshelf",languageHashMap,checkLang);
        Thread.sleep(1000);
        vitalSourceSignInPage.clickOnSignIn();
        vitalSourceSignInPage.signIn(validUsername, validPassword);
        Thread.sleep(1000);
        StudentETextBooksPage studentETextBooksPage = new StudentETextBooksPage(driver, browser, browser_version, true, propertyValues[9]+"student-etextbooks",languageHashMap,checkLang);
        Thread.sleep(1000);
        studentETextBooksPage.logOut();
        Thread.sleep(10000);
    }
}
