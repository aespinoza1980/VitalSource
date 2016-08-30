import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class HighLightMe {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        //Open Application
        driver.navigate().to("http://www.uftHelp.com");
        //Maximize the browser
        driver.manage().window().maximize();
        //Find the element to highlight
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Home')]"));
        //Function call to Highlight the element
        fnHighlightMe(driver,element);


    }

    public static void fnHighlightMe(WebDriver driver,WebElement element) throws InterruptedException{
        //Creating JavaScriptExecuter Interface
        JavascriptExecutor js = (JavascriptExecutor)driver;
        for (int iCnt = 0; iCnt < 3; iCnt++) {
            //Execute javascript
            js.executeScript("arguments[0].style.border='4px groove green'", element);
            Thread.sleep(1000);
            js.executeScript("arguments[0].style.border=''", element);
        }
    }

}