package com.selenium.bootcamp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest {
	
	WebDriver driver;
	@BeforeTest
	@Parameters({"browser","url"})
	public void setUp(String b,String url){
		
		if(b.equalsIgnoreCase("chrome")){
		System.setProperty("webdriver.chrome.driver", "D:\\Webdrivers\\chromedriver.exe");
		ChromeOptions options=new ChromeOptions();
		options.addArguments("—disable-notifications");
		driver=new ChromeDriver(options);
		}else if(b.equalsIgnoreCase("firefox")){
			
			
		}

		driver.get(url);
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys("mercury.bootcamp@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Bootcamp@123");
		driver.findElement(By.id("Login")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	
	@AfterTest
	public void tearDown(){
		
		driver.close();
	}
	
}
