package com.selenium.bootcamp;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	WebDriver driver;
	@BeforeMethod(alwaysRun = true)
	@Parameters({"browser","url","username","password"})
	public void setUp(String b,String url, String UN, String PWD){
		
		if(b.equalsIgnoreCase("chrome")){
		WebDriverManager.chromedriver().setup();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("—disable-notifications");
		driver=new ChromeDriver(options);
		}else if(b.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			
		}

		driver.get(url);
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys(UN);
		driver.findElement(By.id("password")).sendKeys(PWD);
		driver.findElement(By.id("Login")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	
	@AfterMethod(alwaysRun = true)
	public void tearDown(){
		
		driver.close();
	}
	
}
