package TestNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreatePaymentAndFindingErrorMessages {

	WebDriver driver;
	WebDriverWait wait;

	@Test
	public void login(){

		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys("mercury.bootcamp@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Bootcamp@123");
		driver.findElement(By.id("Login")).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		boolean appLauncher=driver.findElement(By.xpath("//span[text()='App Launcher']//ancestor::button")).isDisplayed();
		Assert.assertEquals(appLauncher, true);

	}


	@Test
	public void selectPayments() throws InterruptedException{
		wait=new WebDriverWait(driver, 15);

		WebElement appLauncher=driver.findElement(By.xpath("//span[text()='App Launcher']//ancestor::button"));
		wait.until(ExpectedConditions.elementToBeClickable(appLauncher)).click();

		WebElement viewAll=driver.findElement(By.xpath("//h3[text()='Apps']//following::button[text()='View All']"));
		wait.until(ExpectedConditions.elementToBeClickable(viewAll)).click();		



		WebElement payments=driver.findElement(By.xpath("//p[text()='Payments']//ancestor::a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", payments);
		Thread.sleep(2000); 

		JavascriptExecutor js = (JavascriptExecutor)driver;	
		js.executeScript("arguments[0].click();", payments);

		WebElement newButton=driver.findElement(By.xpath("//div[text()='New']//parent::a"));
		wait.until(ExpectedConditions.elementToBeClickable(newButton)).click();	

		Thread.sleep(2000);
		WebElement saveButton=driver.findElement(By.xpath("//span[text()='Information']//following::span[text()='Save']"));
		wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();

		WebElement EM=driver.findElement(By.xpath("//ul[@class='errorsList']/li"));
		String errorMessage=wait.until(ExpectedConditions.elementToBeClickable(EM)).getText();
		
		System.out.println(errorMessage);
		
		if(errorMessage.contains("These required fields must be completed")){
			System.out.println("Test Case Passed");
		}else{
			System.out.println("Test Case Failed");
		}
		
		driver.close();

	}

}

