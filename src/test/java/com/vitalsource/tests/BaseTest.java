package com.vitalsource.tests;

import com.vitalsource.lib.FileOperation;
import com.vitalsource.properties.Property;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alexis Espinoza on 6/30/16.
 */
public class BaseTest {
    protected WebDriver driver;
    protected String loginPage                  = null;
    protected String validUsername              = null;
    protected String validPassword              = null;
    protected String invalidUsername            = null;
    protected String invalidPassword            = null;
    protected String invalidEmailPass           = null;
    protected String cantFoolCaptchaYet         = null;
    protected String wrongRedeemCode            = null;
    protected String bookLink                   = null;
    protected String vsLoginPage                = null;
    protected HashMap<String, Object> languageHashMap;
    protected String language;
    protected boolean checkLang;
    protected String[] propertyValues;
    protected String browser;
    protected String browser_version    = "6.0";
    protected String os                 = "Windows";
    protected String os_version         = "XP";
    protected String mobileBrowserName;
    protected String mobilePlatform;
    protected String mobileDevice;
    private final String USERNAME       = "yourbrowserstackuser";
    private final String AUTOMATE_KEY   = "yourbrowserstackpass";
    private final String URL            = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    private HashMap<String, ArrayList<String>> osSetup() {
        HashMap<String, ArrayList<String>> operatingSystem = new HashMap<String, ArrayList<String>>();
        String [] osSupported         = {"Windows", "OS X"};
        String [] windows_os_version  = {"XP","7","8","8.1","10"};
        String [] osx_os_version      = {"El Capitan","Yosemite","Mavericks","Mountain Lion","Lion","Snow Leopard"};
        for(int i = 0; i < osSupported.length; i++) {
            operatingSystem.put(osSupported[i],new ArrayList<String>());
            switch (i) {
                case 0:  for (int j = 0; j < windows_os_version.length; j++) {
                                operatingSystem.get(osSupported[i]).add(windows_os_version[j]);
                         }
                         break;
                case 1:  for (int j = 0; j < osx_os_version.length; j++) {
                            operatingSystem.get(osSupported[i]).add(osx_os_version[j]);
                        }
                        break;
            }
        }
        return operatingSystem;
    }
    protected void setUp(String browser, String browser_version, String os, String os_version,
                         String mobileBrowserName, String mobilePlatform, String mobileDevice, String language,boolean checkLang)throws IOException {
        Property properties = new Property();
        HashMap<String, ArrayList<String>> osDesktop = osSetup();
        propertyValues = properties.getPropValues().split(",");
        this.browser         = browser;
        this.browser_version    = browser_version;
        this.os                 = os;
        this.os_version         = os_version;
        this.mobileBrowserName  = mobileBrowserName;
        this.mobilePlatform     = mobilePlatform;
        this.mobileDevice       = mobileDevice;
        this.checkLang          = checkLang;
        this.language           = language;
        this.languageHashMap    = FileOperation.readFileIntoHashMap(System.getProperty("user.dir") + "/src/test/resources/lang/" + language + ".txt", 0);
        //Set capabilities for mobile in BrowserStack
        if(!this.mobileBrowserName.isEmpty()) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", this.mobileBrowserName);
            caps.setCapability("platform", this.mobilePlatform);
            caps.setCapability("device", this.mobileDevice);
            driver = new RemoteWebDriver(new URL(URL), caps);
        } else {
            if(!this.browser_version.isEmpty()) {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("browser", this.browser);
                caps.setCapability("browser_version", this.browser_version);
                caps.setCapability("os", this.os);
                caps.setCapability("os_version", this.os_version);
                caps.setCapability("browserstack.debug", "true");
                driver = new RemoteWebDriver(new URL(URL), caps);
            } else { // using local drivers
                if(this.browser.equals("IE")) {
                    System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/IEDriverServer.exe");
                // Launch InternetExplorerDriver
                    driver = new InternetExplorerDriver();

                }else {
                    if (this.browser.equals("chrome")) {
                        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");//"/usr/bin/google-chrome"
                        System.out.println(System.getProperty("webdriver.chrome.driver"));
                        driver = new ChromeDriver();
                    } else {
                        driver = new FirefoxDriver();

                    }
                }
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        loginPage          = propertyValues[0];
        validUsername      = propertyValues[1];
        validPassword      = propertyValues[2];
        invalidUsername    = propertyValues[3];
        invalidPassword    = propertyValues[4];
        invalidEmailPass   = propertyValues[5];
        cantFoolCaptchaYet = propertyValues[6];
        wrongRedeemCode    = propertyValues[7];
        bookLink           = propertyValues[8];
        vsLoginPage        = propertyValues[9];
    }
    @AfterTest
     public void close() {
        //Shutdown the browser
        driver.quit();
    }

}