package com.MG.testcases;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class testHomeSmoke {
	
public static AndroidDriver driver;
	
	@BeforeMethod
	public void setUp() throws MalformedURLException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability("device", "Android");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "5.0.2");
		capabilities.setCapability("appPackage", "com.gmpuser.app");
		capabilities.setCapability("appActivity", "com.gmpuser.app.ui.activities.SplashActivity");
		capabilities.setCapability("deviceName", "Android HTC Device");
		driver =  new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	public void tearDown(){
	
		driver.quit();
	}

	@Test(priority = 1)
	public void tchome01() throws IOException, InterruptedException{
				
				//Purpose: launch and exit app and take screenshot of app launch

				//Check if app launch is successful
				System.out.println("TestCase 1: Check app launch");
				List<WebElement> launch = driver.findElements(By.id("com.gmpuser.app:id/tv_login_tabstrip"));
				if (launch.size() >= 1) {
					System.out.println("App launch successful");
				}else{
					System.out.println("App launch failed");
				}
				Thread.sleep(3000);
				
				String filename = "C:\\Mohit\\Java Workspaces\\GMP\\ResultImages\\Home\\tchome01.jpg";
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(filename));
	}
	
	@Test(priority = 2)
	public void tchome02() throws IOException, InterruptedException{
				
				//Purpose: Login & Logout Functionality Test
				System.out.println("TestCase 2: Check Login and Logout Functonality");
		
				//Do login
				driver.findElement(By.id("com.gmpuser.app:id/et_email")).sendKeys("mohitnit06@gmail.com");
				driver.findElement(By.id("com.gmpuser.app:id/et_password")).sendKeys("password");
				driver.navigate().back();
				driver.findElement(By.id("com.gmpuser.app:id/btn_login")).click();
				Thread.sleep(10000);
				
				//Check if login is successful
				List<WebElement> login = driver.findElements(By.id("com.gmpuser.app:id/toolbar"));
				
				if (login.size() >= 1) {
					System.out.println("Login successful");
				}else{
					System.out.println("Login Failed");
				}
					
				//Do Logout
				driver.findElement(By.id("com.gmpuser.app:id/toolbar")).findElement(By.className("android.widget.ImageButton")).click();
				driver.findElement(By.id("com.gmpuser.app:id/btn_logout")).click();
				
				//Check if logout is successful
				List<WebElement> logout = driver.findElements(By.id("com.gmpuser.app:id/tv_login_tabstrip"));
				if (logout.size() >= 1) {
					System.out.println("Logout successful");
				}else{
					System.out.println("Logout failed");
				}
				
				//Take screenshot of final screen before ending testcase
				Thread.sleep(10000);
				String filename = "C:\\Mohit\\Java Workspaces\\GMP\\ResultImages\\Home\\tchome02.jpg";
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(filename));
	}
	
	@Test(priority = 3)
	public void tchome03() throws IOException, InterruptedException{
				
		//Purpose: Check values shown in filter window
		System.out.println("TestCase 3: Check the values shown in the Filter Window");
		//Do Login
		driver.findElement(By.id("com.gmpuser.app:id/et_email")).sendKeys("mohitnit06@gmail.com");
		driver.findElement(By.id("com.gmpuser.app:id/et_password")).sendKeys("password");
		driver.navigate().back();
		driver.findElement(By.id("com.gmpuser.app:id/btn_login")).click();
		Thread.sleep(10000);		
		
		//Open filter window
		driver.findElement(By.id("com.gmpuser.app:id/rl_btn_filter")).click();
		
		//Take screenshot of the filter window
		Thread.sleep(2000);
		String filename = "C:\\Mohit\\Java Workspaces\\GMP\\ResultImages\\Home\\tchome03.jpg";
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(filename));
		
		//Read the values found in Filter window
		WebElement bar1 = driver.findElement(By.id("com.gmpuser.app:id/rv_filters"));
		List<WebElement> bar2 = bar1.findElements(By.className("android.widget.LinearLayout"));		
		
		String ExpectedList[] = {"CAR","BIKE","VALEET"};
		String[] ActualList = new String[3];

		for (int i = 0; i < bar2.size(); i++) {
		//	System.out.println(bar2.get(i).findElement(By.id("com.gmpuser.app:id/tv_sublot_name")).getText());
			ActualList[i] = (bar2.get(i).findElement(By.id("com.gmpuser.app:id/tv_sublot_name")).getText());
		}
		
		//Compare the actual strings with expected strings
		for (int i = 0; i < ActualList.length; i++) {
			System.out.println("Expected String is "+ExpectedList[i]+" and Actual String is "+ActualList[i]);
			Assert.assertEquals(ExpectedList[i], ActualList[i]);
		}
		
		//Do Logout
		Thread.sleep(3000);
		driver.navigate().back();
		driver.findElement(By.id("com.gmpuser.app:id/toolbar")).findElement(By.className("android.widget.ImageButton")).click();
		driver.findElement(By.id("com.gmpuser.app:id/btn_logout")).click();
		
	}
	

}
