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

public class CreatePayment {

	WebDriver driver;
	WebDriverWait wait;

	@Test
	public void login() throws InterruptedException{

		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys("mercury.bootcamp@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Bootcamp@123");
		driver.findElement(By.id("Login")).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

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



		WebElement account=driver.findElement(By.xpath("//span[text()='Information']//following::span[text()='Account']//following::input"));
		wait.until(ExpectedConditions.elementToBeClickable(account)).click();	

		WebElement selectAccount=driver.findElement(By.xpath("//div[text()='Credits']"));
		wait.until(ExpectedConditions.elementToBeClickable(selectAccount)).click();	

		WebElement amount=driver.findElement(By.xpath("(//span[text()='Amount'])[2]//following::input"));
		wait.until(ExpectedConditions.elementToBeClickable(amount)).sendKeys("5000");


		WebElement statusSelect=driver.findElement(By.xpath("//span[text()='Status']//following::a[@class='select']"));
		try{
			js.executeScript("arguments[0].click();", statusSelect);
			Thread.sleep(3000);
		}catch(Exception e){
			wait.until(ExpectedConditions.elementToBeClickable(statusSelect)).click();
			Thread.sleep(3000);
		}
		// 

		//Thread.sleep(5000);

		WebElement status=driver.findElement(By.xpath("//a[text()='Draft']"));
		wait.until(ExpectedConditions.elementToBeClickable(status)).click();

		WebElement typeSelect=driver.findElement(By.xpath("(//span[text()='Type'])[2]//following::a[@class='select']"));
		js.executeScript("arguments[0].click();", typeSelect);
		// wait.until(ExpectedConditions.elementToBeClickable(typeSelect)).click();

		Thread.sleep(2000);

		WebElement type=driver.findElement(By.xpath("//a[text()='Sale']"));
		wait.until(ExpectedConditions.elementToBeClickable(type)).click();



		WebElement processingMode=driver.findElement(By.xpath("(//span[text()='Processing Mode']//following::a[@class='select'])"));
		wait.until(ExpectedConditions.elementToBeClickable(processingMode)).click();

		Thread.sleep(2000);

		WebElement mode=driver.findElement(By.xpath("//a[text()='External']"));
		wait.until(ExpectedConditions.elementToBeClickable(mode)).click();

		WebElement saveButton=driver.findElement(By.xpath("//span[text()='Information']//following::span[text()='Save']"));
		wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();

		driver.close();


	}

}

