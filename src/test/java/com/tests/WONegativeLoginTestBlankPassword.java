package com.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static com.utilities.BrowserUtils.verifyTextMatches;

public class WONegativeLoginTestBlankPassword {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        //1.Open browser
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //2.Go to website
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");

        //3.Verify title equals "Web Orders Login"
        System.out.println("Verify title equals 'Web Orders Login': "+verifyTextMatches(driver.getTitle(), "Web Orders Login"));

        //Save the current url
        String currentUrl =driver.getCurrentUrl();

        //Enter username "Tester"
        WebElement userName = driver.findElement(By.id("ctl00_MainContent_username"));
        userName.sendKeys("Tester");

        //Click on Login Button
        WebElement loginButton = driver.findElement(By.id("ctl00_MainContent_login_button"));
        loginButton.click();

        //Verify title still equals "Web Orders Login"
        System.out.println("Title still equals 'Web Orders Login': "+verifyTextMatches(driver.getTitle(), "Web Orders Login"));

        //Verify the current url equals the string saved in step 4

        System.out.println("The current url equals: "+currentUrl+" "+verifyTextMatches(driver.getCurrentUrl(), currentUrl));
    }
}
