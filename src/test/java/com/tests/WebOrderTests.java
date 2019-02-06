package com.tests;

import com.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class WebOrderTests extends TestBase {


    public void login() {
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        WebElement username = driver.findElement(By.id("ctl00_MainContent_username"));
        username.sendKeys("Tester");
        WebElement passWord = driver.findElement(By.id("ctl00_MainContent_password"));
        passWord.sendKeys("test");
        WebElement submit = driver.findElement(By.id("ctl00_MainContent_login_button"));
        submit.click();
        driver.manage().window().maximize();
    }

    @Test
    public void products() {
        //1. Login to Web Orders
        login();
        //2. Click on View All Products Link
        WebElement viewAllProducts = driver.findElement(By.linkText("View all products"));
        viewAllProducts.click();

        //3. Remember all the product names from the table
        List<WebElement> productNames = driver.findElements(By.xpath("//table[@class='ProductsTable']/tbody/tr/td[1]"));
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < productNames.size(); i++) {
            list.add(productNames.get(i).getText());
        }

        //4. Click on View all orders link
        driver.findElement(By.linkText("View all orders")).click();

        //5. Verify that all the values in the Products column match the names from step 4
        List<WebElement> allProduct = driver.findElements(By.xpath("//table[@class='SampleTable']/tbody/tr/td[3]"));
        ArrayList<String> sampleTableProductNames = new ArrayList<String>();
        for (WebElement name : allProduct) {
            sampleTableProductNames.add(name.getText());
        }
        Assert.assertTrue(sampleTableProductNames.containsAll(list));
    }
    public static int getRandomInteger(int max, int min){
        return ((int)(Math.random()*(max-min)))+min;
    }

    @Test
    public void createOrder()  {
        //login to website
        login();
        //Click on order link
        driver.findElement(By.linkText("Order")).click();

        //Select a product (Select different product every time)
        Select select = new Select(driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct")));
        WebElement quantityField = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity"));

        quantityField.sendKeys(Keys.chord(Keys.CONTROL, "a"), String.valueOf(getRandomInteger(10, 1)));
        WebElement discountField =driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtDiscount"));


            switch (getRandomInteger(3,1)) {
                case 1:
                    select.getFirstSelectedOption();
                    discountField.sendKeys(Keys.chord(Keys.CONTROL, "a"),"0.08");
                    System.out.println("Random number is 1 and " +select.getFirstSelectedOption().getText()+" was chosen");
                    break;
                case 2:
                    select.selectByIndex(1);
                    discountField.sendKeys(Keys.chord(Keys.CONTROL, "a"),"0.15");
                    System.out.println("Random number is 2 and FamilyAlbum was chosen");
                    break;
                case 3:
                    select.selectByIndex(2);
                    discountField.sendKeys(Keys.chord(Keys.CONTROL, "a"),"0.1");
                    System.out.println("Random number is 3 and ScreenSaver was chosen");
                    break;
            }
            WebElement customerName =driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName"));
            WebElement streetAddress =driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2"));
            WebElement city =driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3"));
            WebElement zip =driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5"));
            WebElement cardTypesRadioButton =driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1"));
            WebElement cardNumber =driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6"));
            WebElement expireDate =driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1"));

           //Faker information
             String fakeCustomerName =faker.name().fullName();
             String fakeStreetAddress = faker.address().streetAddress();
             String fakeCity = faker.address().city();
             String fakeZip =faker.number().digits(5);
             String fakeCardNumber =faker.number().digits(15);
             String fakeExpiredDate ="0"+faker.number().numberBetween(1,9)+"/"+faker.number().numberBetween(19,23);

        customerName.sendKeys(fakeCustomerName);
             streetAddress.sendKeys(fakeStreetAddress);
             city.sendKeys(fakeCity);
             zip.sendKeys(fakeZip);
             cardTypesRadioButton.click();
             cardNumber.sendKeys(fakeCardNumber);
             expireDate.sendKeys(fakeExpiredDate);

             driver.findElement(By.linkText("Process")).click();
                    }

            @Test
        public void delete(){
        login();
        //Delete a random entry from the table
                //random  8th row with name 'Samuel Clemens'

                List<WebElement> allRowsBefore = driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr"));

        WebElement personNameRow=driver.findElement(By.id("ctl00_MainContent_orderGrid_ctl08_OrderSelector"));
        personNameRow.click();
        driver.findElement(By.id("ctl00_MainContent_btnDelete")).click();
        List<WebElement> allRowsAfter = driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr"));
        List<WebElement> allNames = driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr/td[2]"));
        List<String> allNamesText = new ArrayList<String>();
        for(WebElement name : allNames){
            allNamesText.add(name.getText());
                }
                System.out.println(allNamesText);

        // 4.Verify that table row count decreased by 1
        System.out.println("number of rows before deleting: " + allRowsBefore.size()+" | number of rows after deleting: " + allRowsAfter.size());
        Assert.assertEquals(allRowsAfter.size(), allRowsBefore.size()-1);

        // 5.Verify Name column does not contain deleted person's name
         String personName ="Samuel Clemens";
          Assert.assertFalse(allNamesText.contains(personName));
            }

            @Test
    public void edit(){
        login();
                //Click edit button for any entry 'Paul Brown'
        List<WebElement> firstRowInfo =driver.findElements(By.xpath("//tbody/tr[2]/td"));
        List<String> firstRowEntries = new ArrayList<String>();
                for (int i = 0; i <firstRowInfo.size() ; i++) {
                    if(i!=3){
                       firstRowEntries.add(firstRowInfo.get(i).getText());
                   } else{
                        continue;
                    }
                }
                System.out.println("Info before editing: "+firstRowEntries.toString());
       WebElement editButton =driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_orderGrid\"]/tbody/tr[2]/td[13]/input"));
       editButton.click();
           // Change the quantity to a different amount
              driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(Keys.chord(Keys.CONTROL, "a"),"5");

           //Click calculate
         driver.findElement(By.xpath("//input[@type='submit']")).click();

          //Verify that new quantity is displayed
               String inputQuantity ="5";
               String actualQuantity =driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).getAttribute("value");
               System.out.println("Actual value of quantity: "+actualQuantity);
               Assert.assertEquals(inputQuantity, actualQuantity);

                //Click on Update
                driver.findElement(By.id("ctl00_MainContent_fmwOrder_UpdateButton")).click();
               String quantityAfterUpdate =driver.findElement(By.xpath("//tbody/tr[2]/td[4]")).getText();
               System.out.println("Actual value of quantity after updating: "+actualQuantity);
               Assert.assertEquals(quantityAfterUpdate, inputQuantity);

               //Verify thet other information in that row did not change
               List<WebElement> entriesAfterUpdate =driver.findElements(By.xpath("//tbody/tr[2]/td"));
               List<String> afterUpdateInfo =new ArrayList<String>();
                for (int i = 0; i < entriesAfterUpdate.size(); i++) {
                    if (i != 3) {
                        afterUpdateInfo.add(entriesAfterUpdate.get(i).getText());
                    } else {
                        continue;
                    }
                                }
                System.out.println("After updating the entire info on the row: "+afterUpdateInfo.toString());
                Assert.assertEquals(firstRowEntries, afterUpdateInfo);
            }
                }





