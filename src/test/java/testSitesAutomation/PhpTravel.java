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
        driver.get("https://www.phptravels.net/home/");
    }

    @AfterTest
    public void CloseWebPage() {
        //driver.close(); // Close the window linked with driver
        driver.quit(); // Close the browser
    }

    @Test
    public void fillSearchHotelForm() {
        WebElement searchBox = driver.findElement(By.cssSelector("body.with-waypoint-sticky:nth-child(2) div.select2-drop.select2-display-none.select2-with-searchbox.select2-drop-active:nth-child(14) div.select2-search > input.select2-input"));
        searchBox.click();
        searchBox.sendKeys("Alzer Hotel Istanbul, Istanbul");
        driver.findElement(By.xpath("//div[@class='datepicker -bottom-left- -from-bottom- active']//div[@class='datepicker--nav-action']//*[local-name()='svg']//*[name()='path' and contains(@d,'M 14,12 l ')]")).click();
        driver.findElement(By.xpath("//div[@class='datepicker -bottom-left- -from-bottom- active']//div[@class='datepicker--cell datepicker--cell-day'][contains(text(),'23')]")).click();
        driver.findElement(By.id("//input[@id='checkout']"));
        driver.findElement(By.xpath("//div[@class='datepicker -bottom-left- -from-bottom- active']//div[@class='datepicker--cell datepicker--cell-day'][contains(text(),'31')]")).click();
        driver.findElement(By.xpath("//div[contains(@class,'col o1')]//button[contains(@class,'btn btn-white bootstrap-touchspin-up')][contains(text(),'+')]")).click();
        driver.findElement(By.xpath("//div[contains(@class,'col o1')]//button[contains(@class,'btn btn-white bootstrap-touchspin-up')][contains(text(),'+')]")).click();
        driver.findElement(By.xpath("//div[contains(@class,'col-md-2 col-xs-12')]//button[contains(@class,'btn btn-primary btn-block')][contains(text(),'Search')]")).click();

    }

}
