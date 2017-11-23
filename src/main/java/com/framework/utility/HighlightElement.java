package com.framework.utility;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HighlightElement {
WebDriver driver;
	public static void highLightElement(String xpath, WebDriver driver)
		{
		
		WebElement element = driver.findElement(By.xpath(xpath));
			JavascriptExecutor js=(JavascriptExecutor)driver; 
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
			try 
				{
				Thread.sleep(500);
				} 
			catch (InterruptedException e) {
				System.out.println(e.getMessage());
			} 
			js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element); 
		}
}

