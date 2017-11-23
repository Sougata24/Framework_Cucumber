package com.parent;


import java.io.File;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;

public class BaseClass {

	public static  WebDriver driver;
	
	
	public static void openApplication() throws InterruptedException
	{
		Reporter.log("=====Browser Session Started=====", true);
		//=========IE ===================
		/*File file = new File("_driver/IEDriverServer.exe");
		System. setProperty("webdriver.ie.driver",file.getAbsolutePath());
		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		driver = new InternetExplorerDriver(cap);*/
		//=========Chrome ===================
		File file = new File("_driver/chromedriver.exe");
		System. setProperty("webdriver.chrome.driver",file.getAbsolutePath());
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		System.out.println(driver.manage().window().getSize());
		Thread.sleep(1000);
	}
	
	
	public static void closeApplication()
	{
		driver.quit();
		Reporter.log("=====Browser Session End=====", true);
	}
	
}
