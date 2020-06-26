package testSitesAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 1. Go to darksky.net
 * 2. Verify Feels-Like-temp value is in between Low-temp and High-temp
 *
 * "78˚" --> "78" --> 78
 * "4˚" --> "4" --> 4
 * "-4˚" --> "-4" --> 4
 *
 */

/**
 * Homework 2:
 * 1. Launch Darksky
 * 2. Go to DarkSy API Homepage
 * 3. Click on 'blog Past' link
 * 4. Verify Blog page title is 'Dark Sky Blog'
 * 5. Verify Page Banner Text is SAME as Tile Header-Text (Dark Sky Has a New Home).
 * 6. Verify the Tile-Date is SAME as Blog Date
 * 7. Go Back and Verify user lands on Dark Sky API HomePage
 */

public class DarkSky {
    WebDriver driver;

    @BeforeTest
    public void launchWebPage() {
        System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.darksky.net/");
    }

    @AfterTest
    public void CloseWebPage() {
        driver.quit(); // Close the browser
    }

    @Test
    public void verifyFeelsLikeTempBetweenLowTempAndHighTemp () {
        String feelsLikeTempDegrees = driver.findElement(By.xpath("//span[@class='feels-like-text']")).getText();
        String lowTempDegrees = driver.findElement(By.xpath("//span[@class='low-temp-text']")).getText();
        String highTempDegrees = driver.findElement(By.xpath("//span[@class='high-temp-text']")).getText();

        String[] feelsLikeTemp = feelsLikeTempDegrees.split("\\W");
        String[] lowTemp = lowTempDegrees.split("\\W");
        String[] highTemp = highTempDegrees.split("\\W");

        int feelsLike = Integer.parseInt(feelsLikeTemp[0]);
        int low = Integer.parseInt(lowTemp[0]);
        int high = Integer.parseInt(highTemp[0]);

        boolean isFeelsLikeTempBetweenLowTempandHighTemp = false;

        if (feelsLike > low && feelsLike < high) {
            isFeelsLikeTempBetweenLowTempandHighTemp = true;
        }

        System.out.println("Low Temp: " + low);
        System.out.println("Feels Like Temp: " +feelsLike);
        System.out.println("High Temp: " + high);

        Assert.assertTrue(isFeelsLikeTempBetweenLowTempandHighTemp,"Feels like Temp is not between High Temp and Low Temp");

    }

    @Test
    public void verifyDarkSkyElements () {
        String homePageURL = driver.getCurrentUrl();
        WebElement blogPostLink = driver.findElement(By.xpath("//a[contains(text(),'our blog')]"));
        blogPostLink.click();

        List<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        String expectTitle = "Dark Sky Blog";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,expectTitle);

        String actualPageBanner = driver.findElement(By.xpath("//a[contains(text(),'Dark Sky Has a New Home')]")).getText();
        String expectedPageBanner = "Dark Sky Has a New Home";
        Assert.assertEquals(actualPageBanner,expectedPageBanner,"Banner text does not match");

        String titleDate = driver.findElement(By.xpath("//time[contains(text(),'March 31, 2020')]")).getText();
        String blogDate = driver.findElement(By.xpath("//p[contains(text(),'Mar 31, 2020')]")).getText();

        String parseMonth = titleDate.substring(0,3);
        String parseDate = parseMonth + titleDate.substring(5);

        Assert.assertEquals(blogDate,parseDate,"Dates do not match");

        driver.switchTo().window(tabs.get(0));

        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(actualURL,homePageURL,"Not home page URL");

    }
}
