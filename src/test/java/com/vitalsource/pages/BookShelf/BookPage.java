package com.vitalsource.pages.BookShelf;

import com.vitalsource.pages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.HashMap;

import static org.testng.Assert.assertEquals;
/**
 * Created by Alexis Espinoza on 8/26/16.
 */
public class BookPage extends BasePage {
    public BookPage(WebDriver driver,String browser, String browser_version, boolean navigate, String page, HashMap<String,Object> languageHashMap,boolean checkForLang) {
        super(driver, browser, browser_version, navigate, page, languageHashMap,checkForLang, "Book Page with Language check = " + checkForLang);
    }
    /**
     * Selects the whole text in the book, this activates modal window option and adds a highlight.
     * This should color the text
      */
    public void addHighLight() throws InterruptedException {
        Thread.sleep(1000);
        try {
            scrollDown(1);
            WebElement iFrame= driver.findElement(By.tagName("iframe"));
            driver.switchTo().frame(iFrame);
            iFrame= driver.findElement(By.tagName("iframe"));
            driver.switchTo().frame(iFrame);

            WebElement webElement1 = driver.findElement(By.cssSelector("#main_container > section"));
            WebElement webElement2 = webElement1.findElement(By.cssSelector("#main_container > section > div.content-margin"));
            WebElement webElement3 = webElement2.findElement(By.cssSelector("#overview"));
            WebElement webElement4 = webElement3.findElement(By.cssSelector("#overview > p:nth-child(3)"));


            /*webElement4.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            Actions select = new Actions(driver);
            select.doubleClick(webElement4).build().perform();
                */
            Actions action = new Actions(driver);
            action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();

            driver.switchTo().defaultContent();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#book-wrapper > div.book-slide.vertical > span")));
            WebElement webElement0 = driver.findElement(By.cssSelector("#create-highlight-menu"));
            webElement1 = webElement0.findElement(By.cssSelector("#create-highlight-menu > div.create-highlight-container"));
            webElement2 = webElement1.findElement(By.cssSelector("#create-highlight-menu > div.create-highlight-container > ul"));
            webElement3 = webElement2.findElement(By.cssSelector("#create-highlight-menu > div.create-highlight-container > ul > li:nth-child(2)"));
            webElement4 = webElement3.findElement(By.cssSelector("#create-highlight-menu > div.create-highlight-container > ul > li:nth-child(2) > button"));
            webElement4.click();
            Thread.sleep(1000);
            /*iFrame= driver.findElement(By.tagName("iframe"));
            driver.switchTo().frame(iFrame);
            iFrame= driver.findElement(By.tagName("iframe"));
            driver.switchTo().frame(iFrame);
            webElement1 = driver.findElement(By.cssSelector("#main_container > section"));
            webElement2 = webElement1.findElement(By.cssSelector("#main_container > section > div.content-margin"));
            webElement3 = webElement2.findElement(By.cssSelector("#overview"));
            webElement3.click();
            Thread.sleep(1000);*/

        }catch (AWTException e) {
            System.out.println("Could not scroll down");
        }
    }
    /**
     * Clicks on a highlighted text then selects the delete option and clicks on it
     */
    public void deleteHighLight() throws InterruptedException {
            Thread.sleep(1000);
            WebElement iFrame= driver.findElement(By.tagName("iframe"));
            driver.switchTo().frame(iFrame);
            iFrame= driver.findElement(By.tagName("iframe"));
            driver.switchTo().frame(iFrame);

            WebElement webElement1 = driver.findElement(By.cssSelector("#main_container > section"));
            WebElement webElement2 = webElement1.findElement(By.cssSelector("#main_container > section > div.content-margin"));
            WebElement webElement3 = webElement2.findElement(By.cssSelector("#overview"));
            webElement3.click();

            driver.switchTo().defaultContent();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#book-wrapper > div.book-slide.vertical > span")));
            WebElement webElement0 = driver.findElement(By.cssSelector("#create-highlight-menu"));
            webElement1 = webElement0.findElement(By.cssSelector("#create-highlight-menu > div.create-highlight-container"));
            webElement2 = webElement1.findElement(By.cssSelector("#create-highlight-menu > div.create-highlight-container > ul"));
            webElement3 = webElement2.findElement(By.cssSelector("#create-highlight-menu > div.create-highlight-container > ul > li:nth-child(4)"));
            WebElement webElement4 = webElement3.findElement(By.cssSelector("#create-highlight-menu > div.create-highlight-container > ul > li:nth-child(4) > button"));
            Thread.sleep(1000);
            webElement4.click();
            Thread.sleep(2000);

    }

}
