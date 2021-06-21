package com.selenium.bootcamp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TC004_ErrorMessage extends BaseTest {

	@Test
	public void runErrorMessage() throws InterruptedException {

		
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys("mercury.bootcamp@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Bootcamp$123");
		driver.findElement(By.id("Login")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebDriverWait wait=new WebDriverWait(driver, 15);
		
		WebElement appLauncher=driver.findElement(By.xpath("//span[text()='App Launcher']//ancestor::button"));
		wait.until(ExpectedConditions.elementToBeClickable(appLauncher)).click();
		
		WebElement viewAll=driver.findElement(By.xpath("//h3[text()='Apps']//following::button[text()='View All']"));
		wait.until(ExpectedConditions.elementToBeClickable(viewAll)).click();		
		
		WebElement sales=driver.findElement(By.xpath("//p[text()='Sales']"));
		wait.until(ExpectedConditions.elementToBeClickable(sales)).click();
		Thread.sleep(5000);
				
	    JavascriptExecutor js = (JavascriptExecutor)driver;	
		WebElement tasks=driver.findElement(By.xpath("//a[@title='Tasks']"));
		 js.executeScript("arguments[0].click();", tasks);
		//wait.until(ExpectedConditions.elementToBeClickable(tasks)).click();
		 
		 WebElement addNewTask=driver.findElement(By.xpath("(//*[name()='svg' and @data-key='chevrondown']//ancestor::a)[3]"));
		wait.until(ExpectedConditions.elementToBeClickable(addNewTask)).click();
		//Thread.sleep(5000);
		WebElement newTask=driver.findElement(By.xpath("(//span[text()='New Task'])[1]"));   //(//span[text()='New Task']//ancestor::a)[2]
	//	wait.until(ExpectedConditions.elementToBeClickable(newTask)).click();
		js.executeScript("arguments[0].click();", newTask);
		
		String name="Sarath M";
		
		WebElement searchContacts=driver.findElement(By.xpath("//input[@title='Search Contacts']"));
		wait.until(ExpectedConditions.elementToBeClickable(searchContacts)).sendKeys(name);
		
		try{
			WebElement textFind=driver.findElement(By.xpath("//div[@title='"+name+"']"));
			wait.until(ExpectedConditions.elementToBeClickable(textFind)).click();
			}
			catch(Exception e){
				System.out.println("Search Text is not Found");
			}
			
		WebElement comments=driver.findElement(By.xpath("//textarea[contains(@class,'textarea')]"));
		wait.until(ExpectedConditions.elementToBeClickable(comments)).sendKeys("SALES FORCE Automation Using Selenium");
			
		WebElement save=driver.findElement(By.xpath("//span[text()='Save']//parent::button"));
		wait.until(ExpectedConditions.elementToBeClickable(save)).click();
						
		WebElement errorMessage=driver.findElement(By.xpath("//ul[@class='errorsList']/li"));
		String EM=wait.until(ExpectedConditions.elementToBeClickable(errorMessage)).getText();
		if(EM.contains("Subject")){
			System.out.println("Test Case Passed");
			
		}
		else{
			
			System.out.println("Test Case Failed");
		}
		
		
		
	}
		
		
		
}

