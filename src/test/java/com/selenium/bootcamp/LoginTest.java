package com.selenium.bootcamp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest{
	
	public static WebDriver driver;
	@Test(priority=1,dataProvider="testdata")
	public void runLoginTest(String UN,String PWD){
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("password")).sendKeys(PWD);
		driver.findElement(By.id("Login")).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		boolean appLauncher=driver.findElement(By.xpath("//span[text()='App Launcher']//ancestor::button")).isDisplayed();
		Assert.assertEquals(appLauncher, true);
		
	}
	
	@Test(priority=2,dependsOnMethods = { "runLoginTest" })
	public void clickAppLaunch() throws InterruptedException{
		
		WebDriverWait wait=new WebDriverWait(driver, 15);
		WebElement appLauncher=driver.findElement(By.xpath("//span[text()='App Launcher']//ancestor::button"));
		wait.until(ExpectedConditions.elementToBeClickable(appLauncher)).click();
		
		WebElement viewAll=driver.findElement(By.xpath("//h3[text()='Apps']//following::button[text()='View All']"));
		wait.until(ExpectedConditions.elementToBeClickable(viewAll)).click();		
		
		
		
		WebElement payments=driver.findElement(By.xpath("//p[text()='Payments']//ancestor::a"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", payments);
		Thread.sleep(2000); 
		
		JavascriptExecutor js = (JavascriptExecutor)driver;	
		 js.executeScript("arguments[0].click();", payments);
		
	}

	@DataProvider(name="testdata", indices = {0,1})
	public Object[][] TestDataFeed() throws IOException{

		String path = System.getProperty("user.dir");
		String FilePath = path + "\\TestingData.xlsx";
		System.out.println(FilePath);
		FileInputStream fis = new FileInputStream(FilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
	      
		int rowTotal = sheet.getLastRowNum();
		int noOfColumns = sheet.getRow(0).getLastCellNum();
		
		Object [][] data=new Object[rowTotal][noOfColumns];
		
		for(int i=1;i<=rowTotal;i++){
			
			for(int j=0;j<noOfColumns;j++){
			
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(j);
			   	//System.out.println(cell);
				System.out.println(sheet.getRow(i).getCell(j).getStringCellValue());
				data[i-1][j]=sheet.getRow(i).getCell(j).getStringCellValue();
				
			}
		}
		
		/*data[0][0]="mercury.bootcamp@testleaf.com";
		data[0][1]="Bootcamp@123";
		
		data[1][0]="username2@gmail.com";
		data[1][1]="Password2";*/
		System.out.println(data);
	return data;

	
	}
	
}
