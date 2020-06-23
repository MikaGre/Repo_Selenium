package testSitesAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
/**
 * 1. Login to Facebook
 * 2. Fill below data in Sign Up form
 *      First name
 *      Last name
 *      Email-address
 *      Password(s)
 *      Click Sign Up button
 * 3. Verify Error msg : "Please choose a gender. You can change who can see this later."
 */

public class facebook {
    WebDriver driver;

    @BeforeTest
    public void launchWebPage() {
        System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://www.facebook.com/");
    }

    @AfterTest
    public void CloseWebPage() {
        driver.quit(); // Close the browser
    }

    @Test
    public void verifyFailedFacebookSignUpMessage () { //leave gender selection blank
        WebElement firstNameBox = driver.findElement(By.xpath("//input[@name='firstname']"));
        WebElement lastNameBox = driver.findElement(By.xpath("//input[@name='lastname']"));
        WebElement emailMailBox = driver.findElement(By.xpath("//input[@name='reg_email__']"));
        WebElement reEnterEmailBox = driver.findElement(By.name("reg_email_confirmation__"));
        WebElement passwordBox = driver.findElement(By.xpath("//input[@name='reg_passwd__']"));
        WebElement signUpButton = driver.findElement(By.xpath("//button[@name='websubmit']"));

        firstNameBox.sendKeys("Tester");
        lastNameBox.sendKeys("Testers");
        emailMailBox.sendKeys("TestyTheTester@gmail.com");
        reEnterEmailBox.sendKeys("TestyTheTester@gmail.com");
        passwordBox.sendKeys("password123");
        signUpButton.click();

        String expectedText = "Please choose a gender. You can change who can see this later.";
        String actualText = driver.findElement(By.xpath("//div[contains(text(),'Please choose a gender.')]")).getText();

        Assert.assertEquals(actualText,expectedText,"Failed to select gender message does not match");

    }

}
