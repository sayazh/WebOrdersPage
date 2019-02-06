package com.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static com.utilities.BrowserUtils.verifyTextMatches;

public class WOPositiveLoginTests {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        //1.Open browser
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //2.Go to website
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");

        //3.Verify title equals "Web Orders Login"
        System.out.println("Verify title equals 'Web Orders Login': "+verifyTextMatches(driver.getTitle(), "Web Orders Login"));

        //4.Enter username "Tester"
        WebElement userName = driver.findElement(By.id("ctl00_MainContent_username"));
        userName.sendKeys("Tester");

        //5.Enter passWord "test"
        WebElement passWord =driver.findElement(By.id("ctl00_MainContent_password"));
        passWord.sendKeys("test");

        //6.Click on Login Button
        WebElement loginButton =driver.findElement(By.id("ctl00_MainContent_login_button"));
        loginButton.click();

        //7.Verify title equals "Web Orders"
        System.out.println("Verify title equals 'Web Orders': "+verifyTextMatches(driver.getTitle(),"Web Orders"));

        //8.Verify url equals
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl ="http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders";
        System.out.println("Verify url equals "+verifyTextMatches(driver.getCurrentUrl(),"http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders"));
        System.out.println("Actual url: "+actualUrl);
        System.out.println("Expected url: "+expectedUrl);
    }
}
