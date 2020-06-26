package testSitesAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PhpTravel {
    WebDriver driver;

    @BeforeTest
    public void launchWebPage() {
        System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://www.phptravels.net/home/");
    }

    @AfterTest
    public void CloseWebPage() {
        //driver.close(); // Close the window linked with driver
        driver.quit(); // Close the browser
    }

    @Test
    public void fillSearchHotelForm() {
        WebElement destinationBox = driver.findElement(By.cssSelector("body.with-waypoint-sticky:nth-child(2) div.select2-drop.select2-display-none.select2-with-searchbox.select2-drop-active:nth-child(15) div.select2-search > input.select2-input"));
        destinationBox.sendKeys("");
    }

}
