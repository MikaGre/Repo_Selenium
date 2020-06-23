package ClassWork;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.Set;

public class FirstClass {
    WebDriver driver;

    @BeforeTest
    public void launchWebPage() {
        System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.get("https://www.naukri.com/");
        //driver.get("https://www.amazon.com/");
        driver.get("https://www.google.com/");
    }

    @AfterTest
    public void CloseWebPage() {
        //driver.close(); // Close the window linked with driver
        driver.quit(); // Close the browser
    }



/*    public void refresh() {
        driver.navigate().refresh();
        driver.get(driver.getCurrentUrl());
        driver.navigate().back();
        driver.navigate().forward();
    */

    @Test
    public void closePopUps () throws InterruptedException {
        String primary = driver.getWindowHandle();
        Thread.sleep(2000);
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(primary)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
    }

    @Test
    public void verifyUrlTitle() {
        String actualPageTitle = driver.getTitle();
        String expectedPageTitle = "Google";
        Assert.assertEquals(actualPageTitle,expectedPageTitle,"Page title did not match");
    }

    @Test
    public void verifyUrl() {
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.google.com/";
        Assert.assertEquals(actualUrl,expectedUrl,"URL did not match");
        driver.close();
    }

    @Test
    public void googleSearch() {
        WebElement searchBox =  driver.findElement(By.xpath("//input[@name='q']"));
        searchBox.sendKeys("Dune\n");
        WebElement duneLink = driver.findElement(By.xpath("//h3[contains(text(),'The Official Dune Website')]"));
        duneLink.click();
    }

    @Test
    public void amazonSearch() {
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Face Mask");
        driver.findElement(By.xpath("//div[@class='nav-search-submit nav-sprite']//input[@class='nav-input']")).click();
        WebElement faceMask = driver.findElement(By.xpath("//div[22]//div[1]//span[1]//div[1]//div[1]//span[1]//a[1]//div[1]//img[1]"));
        faceMask.click();
    }



}
