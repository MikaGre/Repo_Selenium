package testSitesAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestAppointmentSite {
    WebDriver driver;

    @BeforeTest
    public void launchWebPage() {
        System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://katalon-demo-cura.herokuapp.com/");
    }

    @AfterTest
    public void CloseWebPage() {
        //driver.close(); // Close the window linked with driver
        driver.quit(); // Close the browser
    }

    @Test
    public void failedLogin() {
      driver.findElement(By.id("btn-make-appointment")).click();
      driver.findElement(By.id("txt-username")).sendKeys("John");
      driver.findElement(By.id("txt-password")).sendKeys("password");
      driver.findElement(By.id("btn-login")).click();
      String expectedFailedLoginText = "Login failed! Please ensure the username and password are valid.";
      String actualLoginText = driver.findElement(By.xpath("//p[@class='lead text-danger']")).getText();
      Assert.assertEquals(actualLoginText,expectedFailedLoginText,"Failed login message did not match");

    }
    @Test
    public void successfulLogin () {
        driver.findElement(By.id("btn-make-appointment")).click();
        driver.findElement(By.id("txt-username")).sendKeys("John Doe");
        driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
        driver.findElement(By.id("btn-login")).click();
        String expectLoginText = "Make Appointment";
        String actualLoginText = driver.findElement(By.xpath("//h2[contains(text(),'Make Appointment')]")).getText();
        Assert.assertEquals(actualLoginText,expectLoginText,"successfulLogin Message did not match");
    }

    @Test
    public void makeAppointment () {
        successfulLogin();
        driver.findElement(By.id("combo_facility")).click();
        driver.findElement(By.xpath("//option[contains(text(),'Seoul CURA Healthcare Center')]"));
        driver.findElement(By.id("chk_hospotal_readmission")).click();
        driver.findElement(By.id("radio_program_none")).click();
        driver.findElement(By.id("txt_visit_date")).click();
        driver.findElement(By.xpath("//td[@class='day'][contains(text(),'27')]")).click();
        driver.findElement(By.id("txt_comment")).sendKeys("This is a test");
        driver.findElement(By.id("btn-book-appointment")).click();
        String expectedAppointmentConfirmationText = "Appointment Confirmation";
        String actualAppointmentConfirmationText = driver.findElement(By.xpath("//h2[contains(text(),'Appointment Confirmation')]")).getText();
        Assert.assertEquals(actualAppointmentConfirmationText,expectedAppointmentConfirmationText,"Appointment confirmation text did not match");
    }


}
