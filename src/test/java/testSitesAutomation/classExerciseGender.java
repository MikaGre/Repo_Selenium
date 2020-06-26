package testSitesAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
/**
 * 1. Launch facebook.com
 * 2. verify your gender radio-button is not selected
 * 3. if your gender radio-button is not selected; then select the same
 * 4. verify the gender radio-button is now selected
 *
 */
public class classExerciseGender {
    @Test
    public void verifyGenderRadioButton() {
        System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://www.facebook.com/");

        WebElement customGenderRadioButton = driver.findElement(By.xpath("//input[@type='radio' and @value='-1']"));

        Assert.assertFalse(customGenderRadioButton.isSelected(),"Custom Gender Radio is selected");

        if (!customGenderRadioButton.isSelected()) {
            customGenderRadioButton.click();
        }

        Assert.assertTrue(customGenderRadioButton.isSelected(),"Custom Gender Radio not selected");

        driver.quit();

    }

    @Test
    public void selectDOB() {
        System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://www.facebook.com/");

        WebElement monthDropDown = driver.findElement(By.id("month"));
        WebElement dayDropDown = driver.findElement(By.id("day"));
        WebElement yearDropDown = driver.findElement(By.id("year"));

        Select month = new Select(monthDropDown);
        Select day = new Select(dayDropDown);
        Select year = new Select(yearDropDown);

        month.selectByVisibleText("Oct");
        day.selectByVisibleText("12");
        year.selectByVisibleText("1994");

        String m = "Oct";
        String d = "12";
        String y = "1994";

    }
}
