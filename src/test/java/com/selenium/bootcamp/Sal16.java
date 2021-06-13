package com.selenium.bootcamp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Sal16 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String strDashboard = "Salesforce Automation by Sharathmathi";
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");
		
		System.setProperty("webdriver.chrome.driver", "D:\\Webdrivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(chromeOptions);
		
		driver.get("https://login.salesforce.com");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
		
		//1. Login to https://login.salesforce.com
		driver.findElement(By.id("username")).sendKeys("mercury.bootcamp@testleaf.com");
        driver.findElement(By.id("password")).sendKeys("Bootcamp@123");
        driver.findElement(By.id("Login")).click();
        
		//2. Click on the toggle menu button from the left corner
        webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")))).click();
        
        //3. Click View All and click Dashboards from App Launcher
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@class='slds-button' and text()='View All']")))).click();
        WebElement dashBoards = driver.findElement(By.xpath("//p[@class='slds-truncate' and text()='Dashboards']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", dashBoards);
        
        //4. Click on the New Dashboard option
        webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@title='New Dashboard']")))).click();
        
        int size = driver.findElements(By.tagName("iframe")).size();
        driver.switchTo().frame(0);
        //5. Enter Name as 'Salesforce Automation by Your Name ' and Click on Create.
        webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@id='dashboardNameInput']")))).sendKeys(strDashboard);
        
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@id='submitBtn' and text()='Create']")))).click();
        
        Thread.sleep(5000);
        int size1 = driver.findElements(By.tagName("iframe")).size();
        System.out.println(size1);
        driver.switchTo().frame(0);
        //6.Click on Save and Verify Dashboard name.
        WebElement until = webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[text()='"+strDashboard+"']//following::button[text()='Save']"))));
        js.executeScript("arguments[0].click();", until);
       
        
        String strToastMsg = webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']//ancestor::div[@class='toastContent slds-notify__content']")))).getText();
        
        //Expected ResultThe New Dashboard is created Successfully
        if(strToastMsg.equalsIgnoreCase("Dashboard saved")) {
        	System.out.println("Dashboard saved successfully");
        }else {
        	System.out.println("Dashboard save unsuccessfull");
        }


	}

}