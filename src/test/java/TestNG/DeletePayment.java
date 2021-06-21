package TestNG;

import java.util.List;
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

public class DeletePayment {

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

		WebElement td7=driver.findElement(By.xpath("//td[7]"));
		wait.until(ExpectedConditions.elementToBeClickable(td7)).isDisplayed();

		List<WebElement> status=driver.findElements(By.xpath("//td[7]"));
		int count=0;
		for(WebElement statusValue:status){

			count++;
			String s=statusValue.getText();
			System.out.println(s);

			if(s.equalsIgnoreCase("Draft")){
				WebElement td7MoreActions=driver.findElement(By.xpath("(//td[7]//following::*[name()='svg' and @class='slds-icon slds-icon-text-default slds-icon_xx-small']/ancestor::a)["+count+"]"));
				wait.until(ExpectedConditions.elementToBeClickable(td7MoreActions)).click();

				WebElement delete=driver.findElement(By.xpath("//div[text()='Delete']//parent::a"));
				wait.until(ExpectedConditions.elementToBeClickable(delete)).click();

				break;
			}
		}

		WebElement deleteConfirm=driver.findElement(By.xpath("//span[text()='Delete']//parent::button"));
		wait.until(ExpectedConditions.elementToBeClickable(deleteConfirm)).click();
		
		driver.close();
	}

}

