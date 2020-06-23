package testSitesAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * 1. Go to darksky.net
 * 2. Verify Feels-Like-temp value is in between Low-temp and High-temp
 *
 * "78˚" --> "78" --> 78
 * "4˚" --> "4" --> 4
 * "-4˚" --> "-4" --> 4
 *
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
    public void verifyFeelsLikeTemp() {
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

        System.out.println("High Temp: " + high);
        System.out.println("Low Temp: " + low);
        System.out.println("Feels Like Temp: " +feelsLike);

        Assert.assertTrue(isFeelsLikeTempBetweenLowTempandHighTemp,"Feels like Temp is not between High Temp and Low Temp");

    }
}
