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
/*
Launch Amazon
Enter "Carl BUCHERER Ladies' 18 K Gold Tank Wrist Watch with Diamonds. Super-Slim. Switzerland" in search
Click Search icon
Click Product icon/name to go to Product detail page
Verify "Only 1 left in stock - order soon." msg above "Add to Cart" button
Click "Add To Cart" button
Click "Cart" button
Verify "Only 1 left in stock - order soon." msg shows up after the product name
From Qty dropdown : Select any other quantity than 1
Verify warning msg  "This seller has only 1 of these available.
    To see if more are available from another seller, go to the product detail page." shows up
*/

public class Amazon {
    WebDriver driver;
    @BeforeTest
    public void browserSetUP () {
        System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");
    }

    @AfterTest
    public void browserClose () {
       // driver.quit();
    }

    @Test
    public void GoldTankWristWatch() throws InterruptedException {
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Carl BUCHERER Ladies' 18 K Gold Tank Wrist Watch with Diamonds. Super-Slim. Switzerland");

        WebElement searchButton = driver.findElement(By.xpath("//input[@type='submit' and @class='nav-input']"));
        searchButton.click();

        WebElement carl18kGoldWatchLink = driver.findElement(By.xpath("//div/following::img[@src='https://m.media-amazon.com/images/I/51Pz8pWASLL._AC_UL320_.jpg']"));
        carl18kGoldWatchLink.click();

        Thread.sleep(1000);

        WebElement amountLeftInStockText = driver.findElement(By.xpath("//span[contains(text(),'Only 1 left')]"));
        String expectedStockMessage = "Only 1 left in stock - order soon.";
        String acutalStockMes = amountLeftInStockText.getText();
        Assert.assertEquals(acutalStockMes,expectedStockMessage,"Text did not match");

        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));
        addToCartButton.click();

        Thread.sleep(1000);

        WebElement cartButton = driver.findElement(By.xpath("//span/child::a[@id='hlb-view-cart-announce']"));
        cartButton.click();

        Thread.sleep(1000);

        WebElement cartStockUnderItemName = driver.findElement(By.xpath("//span[contains(text(),'Only 1 left in stock - order soon.')]"));
        String actualCartStockMess = cartStockUnderItemName.getText();
        Assert.assertEquals(actualCartStockMess,expectedStockMessage,"Text did not match");

        Thread.sleep(1000);

        WebElement qtyDropDownButton = driver.findElement(By.id("a-autoid-0"));
        qtyDropDownButton.click();
        Thread.sleep(1000);
        WebElement qtyNum = driver.findElement(By.id("dropdown1_6"));
        qtyNum.click();

        Thread.sleep(1000);
        WebElement sellerOnlyHasOneMessage = driver.findElement(By.xpath("//span[contains(text(),'This seller has only 1 of these available. To see')]"));
        String expectedWarning = "This seller has only 1 of these available. To see if more are available from another seller, go to the product detail page.";
        String actualsellerWarning = sellerOnlyHasOneMessage.getText();
        Assert.assertEquals(actualsellerWarning,expectedWarning,"Text did not match");












    }
}
