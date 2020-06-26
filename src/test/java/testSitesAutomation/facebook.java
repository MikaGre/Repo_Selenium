package testSitesAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.ref.SoftReference;
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

/**
 * Homepage 2:
 * Sign Up Flow:
 * Enter data in all fields as per your wish but use emailAddress as given below.
 * Verify user get "Please enter a valid mobile number or email address." for below email addresses:
 * 1. test####
 * 2. %%winvalied$$$
 * 3. %%$$emailAddress.co^^
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
    public void verifyFacebookGenderErrorMessage () { //leave gender selection blank
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

    @Test
    public void verifyFacebookInvalidMobileNumAndEmailMessage () throws InterruptedException {
        WebElement firstNameBox = driver.findElement(By.xpath("//input[@name='firstname']"));
        WebElement lastNameBox = driver.findElement(By.xpath("//input[@name='lastname']"));
        WebElement emailMailBox = driver.findElement(By.xpath("//input[@name='reg_email__']"));
        WebElement passwordBox = driver.findElement(By.xpath("//input[@name='reg_passwd__']"));
        WebElement signUpButton = driver.findElement(By.xpath("//button[@name='websubmit']"));
        WebElement monthDropDown = driver.findElement(By.id("month"));
        WebElement dayDropDown = driver.findElement(By.id("day"));
        WebElement yearDropDown = driver.findElement(By.id("year"));

        String gender = "Female";
        String genderButtonXpath = "//label[text()='" + gender + "']/preceding-sibling::input";
        WebElement genderRadioButton = driver.findElement(By.xpath(genderButtonXpath));

        Select month = new Select(monthDropDown);
        Select day = new Select(dayDropDown);
        Select year = new Select(yearDropDown);
        String[] invalidEmails = new String[]{"test####","%%winvalied$$$","%%$$emailAddress.co^^"};

        for (String string : invalidEmails) {
            firstNameBox.sendKeys("Tester");
            lastNameBox.sendKeys("Test");
            emailMailBox.sendKeys(string);
            //reEnterEmailBox.sendKeys(string);
            passwordBox.sendKeys("password123");
            month.selectByVisibleText("Oct");
            day.selectByVisibleText("12");
            year.selectByVisibleText("1994");
            genderRadioButton.click();
            signUpButton.click();

            Thread.sleep(1000);
            String actualText = driver.findElement(By.xpath("//div[contains(text(),'Please enter a valid mobile ')]")).getText();
            String expectedText = "Please enter a valid mobile number or email address.";

            Assert.assertEquals(actualText,expectedText,"Invalid error message text do not match");

            emailMailBox.clear();
            firstNameBox.clear();
            lastNameBox.clear();
            passwordBox.clear();
        }

    }

}
