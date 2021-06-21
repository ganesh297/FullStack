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

public class EditPayment {

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

//		wait.until(ExpectedConditions.elementToBeClickable(payments)).click();		
		JavascriptExecutor js = (JavascriptExecutor)driver;	
		js.executeScript("arguments[0].click();", payments);
		Thread.sleep(2000); 
		
		try{

		WebElement moreActions=driver.findElement(By.xpath("//table[@role='grid']//tr[1]//a[contains(@class,'rowActionsPlaceHolder')]"));
		wait.until(ExpectedConditions.elementToBeClickable(moreActions)).click();	
		}catch(Exception e){
			
			WebElement moreActions=driver.findElement(By.xpath("//table[@role='grid']//tr[1]//a[contains(@class,'rowActionsPlaceHolder')]s"));
			wait.until(ExpectedConditions.elementToBeClickable(moreActions)).click();		
		}

		WebElement edit=driver.findElement(By.xpath("//div[text()='Edit']//parent::a"));
		wait.until(ExpectedConditions.elementToBeClickable(edit)).click();		

		WebElement statusSelect=driver.findElement(By.xpath("//h2[contains(text(),'Edit')]//following::span[text()='Status']//following::a"));
		wait.until(ExpectedConditions.elementToBeClickable(statusSelect)).click();	
		
		WebElement cancelled=driver.findElement(By.xpath("//a[text()='Canceled']"));
		wait.until(ExpectedConditions.elementToBeClickable(cancelled)).click();	
		
		WebElement CD=driver.findElement(By.xpath("//span[text()='Cancellation Date']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", CD);
		Thread.sleep(2000); 

		driver.close();
		
	}

}

