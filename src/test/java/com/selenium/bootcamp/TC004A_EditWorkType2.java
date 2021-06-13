package com.selenium.bootcamp;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC004A_EditWorkType2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        
        System.setProperty("webdriver.chrome.driver", "D:\\Webdrivers\\chromedriver.exe");
	
        //Instantiating driver object
        WebDriver driver = new ChromeDriver(chromeOptions);
        
        driver.get("https://login.salesforce.com");
        driver.manage().window().maximize();
        
        String strOperatingHours = "test1";
        driver.findElement(By.id("username")).sendKeys("mercury.bootcamp@testleaf.com");
        driver.findElement(By.id("password")).sendKeys("Bootcamp$123");
        driver.findElement(By.id("Login")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        
        WebElement findElement = driver.findElement(By.xpath("//div[@class='slds-icon-waffle']"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(findElement)).click();
        
        
        WebElement findElement2 = driver.findElement(By.xpath("//button[@class='slds-button' and text()='View All']"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(findElement2)).click();
        
        WebElement findElement3 = driver.findElement(By.xpath("//p[@class='slds-truncate' and text()='Work Types']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", findElement3);
        
        WebElement findElement4 = driver.findElement(By.xpath("//a[@title='New']"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(findElement4)).click();;
        
        WebElement findElement5 = driver.findElement(By.xpath("//span[text()='Work Type Name']/parent::label/following-sibling::input"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(findElement5)).sendKeys("Salesforce Project");
     
        WebElement findElement6 = driver.findElement(By.xpath("//span[text()='Description']/parent::label/following-sibling::textarea"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(findElement6)).sendKeys("Specimen");
           
        WebElement findElement7 = driver.findElement(By.xpath("//span[text()='Estimated Duration']/parent::label/following-sibling::input"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(findElement7)).sendKeys("7");
           
        WebElement findElement8 = driver.findElement(By.xpath("//input[@title='Search Operating Hours']"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(findElement8)).click();
            
        WebElement findElement9 = driver.findElement(By.xpath("//span[@title='New Operating Hours']"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(findElement9)).click();
        
        WebElement findElement10 = driver.findElement(By.xpath("//span[text()='Name']/parent::label/following-sibling::input"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(findElement10)).sendKeys("test1");
                
        WebElement findElement11 = driver.findElement(By.xpath("//span[text()='Information']/parent::h3/following::span[text()='Description']/parent::label/following-sibling::textarea"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(findElement11)).sendKeys("test1");
       
        WebElement findElement12 = driver.findElement(By.xpath("//span[text()='Information']/parent::h3/following::button[@title='Save']"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(findElement12)).click();
              
        WebElement findElement13 = driver.findElement(By.xpath("//span[text()='Description']/parent::h3/following::button[@title='Save']"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(findElement13)).click();
                
        String strToastMsg = driver.findElement(By.xpath("//div[@class='slds-align-middle slds-hyphenate']/span[contains(@class,'toastMessage')]")).getText();
        System.out.println("strToastMsg:"+strToastMsg);
        
        if(strToastMsg.equalsIgnoreCase("Operating Hours \"" + strOperatingHours + "\" was created.")) {
        	System.out.println("The message displayed successfully is : "+strToastMsg);
        }else {
        	System.out.println("The message displayed unsuccessfully is : "+strToastMsg);
        }
           
        //Closing the browser
        driver.quit();
	}
		
		
		
}

