import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alex on 16.12.2016.
 */
public class bingScript {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
        // Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // And now use this to visit Bing
        driver.navigate().to("https://www.bing.com/");
        WebDriverWait wait = new WebDriverWait(driver,20);
        // Go to the image section
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='scpl1']")));
        driver.findElement(By.xpath("//a[@id='scpl1']")).click();
        // Title waiting
        wait.until(ExpectedConditions.titleContains("Лента изображений Bing"));
        // Scroll the page and check the contents
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
        List<WebElement> elements0;
        List<WebElement> elements1;
        elements0 = driver.findElements(By.xpath("//div[@class='dgControl']/ul/li"));
        for (int i = 0; i < 2; i++)
        {
            int size0 = elements0.size();
            jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            wait.until(ExpectedConditions.visibilityOf(elements0.get(size0-1)));
            elements1 = driver.findElements(By.xpath("//div[@class='dgControl']/ul/li"));
            int size1 =  elements1.size();
            if(size1 > size0)
            {
                System.out.println("Contents uploaded");
            }
        }
        jsExecutor.executeScript("window.scrollTo(0,0);");
        // Input in search field
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("automatio");
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//input[@id='sb_form_q']"), "aria-expanded", "true"));
        driver.findElement(By.xpath("//strong[text() = 'n']")).click();
        // Work with filter "Date"
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text() = 'Дата']")));
        driver.findElement(By.xpath("//span[text() = 'Дата']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text() = 'В прошлом месяце']")));
        driver.findElement(By.xpath("//span[text() = 'В прошлом месяце']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='OverlayIFrame']")));
        // Select first image
        driver.findElement(By.xpath("//div[@class='imgres']/div[@class='dg_u'][1]")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='iol_imw']")));
        driver.switchTo().frame("OverlayIFrame");
        // Work with slideshow
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='iol_navr']")));
        driver.findElement(By.xpath("//a[@id='iol_navr']")).click();
        wait.until(ExpectedConditions.attributeContains(By.xpath("//a[@idx='1']"),"class","iol_fst iol_fsst"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='iol_navl']")));
        driver.findElement(By.xpath("//a[@id='iol_navl']")).click();
        wait.until(ExpectedConditions.attributeContains(By.xpath("//a[@idx='0']"),"class","iol_fst iol_fsst"));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class = 'mainImage accessible nofocus']")));
        driver.findElement(By.xpath("//img[@class = 'mainImage accessible nofocus']")).click();
        Set<String> handles = driver.getWindowHandles();
        if(handles.size()>1)
        {
            System.out.println("Image was uploaded");
        }
        driver.quit();
    }
}
