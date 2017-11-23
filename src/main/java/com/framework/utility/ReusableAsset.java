package com.framework.utility;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReusableAsset {

	                public static WebDriver getDriver;             
	                
	                // Enter Text
	                public static WebDriver enterText(String xPath ,String value, WebDriver getDriver)
	                {
	                                JavascriptExecutor executor = (JavascriptExecutor)getDriver;                                
	                                WebElement textBox = getDriver.findElement(By.xpath(xPath));
	                                textBox.click();
	                                executor.executeScript("arguments[0].value = arguments[1];", textBox,value);
	                                
	                                return getDriver;
	                }
	                  
	                public static WebDriver selectDropdown(String xPath ,String option, WebDriver getDriver) throws InterruptedException
	                {
	                                  WebElement dropDown = getDriver.findElement(By.xpath(xPath));
	                                  dropDown.click();
	                                  Thread.sleep(500);
	                                  String dropdownOption = xPath+"/option["+option+"]";
	                                  dropDown = getDriver.findElement(By.xpath(dropdownOption));
	                                  dropDown.click();
	                                  Thread.sleep(1000);
	                                  
	                                  return getDriver;
	                }
	                public static WebDriver buttonClick(String xPath , WebDriver getDriver) throws InterruptedException
	                {
	                                WebElement bttn_click = getDriver.findElement(By.xpath(xPath));
	                                HighlightElement.highLightElement(xPath,getDriver);
	                                bttn_click.click();  
	                                 Thread.sleep(1000);
	                                return getDriver;
	                }
	                public static WebDriver radioClick(String xPath , WebDriver getDriver) throws InterruptedException
	                {
	                                WebElement radio_click = getDriver.findElement(By.xpath(xPath));
	                              //  HighlightElement.highLightElement(getDriver, radio_click);
	                                radio_click.click();  
	                                 Thread.sleep(500);
	                                return getDriver;
	                }
	                public static WebDriver autoCompleteBox(String xPath ,String value, WebDriver getDriver) throws InterruptedException
	                {
	                                JavascriptExecutor executor = (JavascriptExecutor)getDriver;  
	                                
	                                WebElement autoCompleteBox = getDriver.findElement(By.xpath(xPath));
	                                autoCompleteBox.click();
	                                autoCompleteBox.sendKeys(Character.toString(value.charAt(0)));
	                                Thread.sleep(1000);
	                                autoCompleteBox.sendKeys(Keys.DOWN);
	                                Thread.sleep(1000);
	                                autoCompleteBox.click();
	                                executor.executeScript("arguments[0].value = arguments[1];", autoCompleteBox,value);
	                                
	                                return getDriver;
	                }
	                public static String getLebelTest(String xPath , WebDriver getDriver)
	                {
	                	
	                	WebElement getLebel = getDriver.findElement(By.xpath(xPath));
	                	String getLebelText = getLebel.getText();
	                	getDriver.switchTo().defaultContent();
	                	System.out.println("Test Label :  "+getLebelText);
	                	return getLebelText;
	                }
	                public static String getCaseID(String class_Identifier , WebDriver getDriver)
	                {
	                	 String Case_ID;
	                	  WebElement element = getDriver.findElement(By.className(class_Identifier));
	                	  Case_ID = element.getText();
	                	  Case_ID = Case_ID.substring(1, Case_ID.length() - 1);
	                	  System.out.println(Case_ID);
	                	  getDriver.switchTo().defaultContent();
	                	  
	                	return Case_ID;
	                }
	                public static WebDriver searchCase(String Case_ID , WebDriver getDriver) throws InterruptedException
	                {
	          		  WebElement element = getDriver.findElement(By.className("SearchResultsPromptText"));
	        		  JavascriptExecutor executor = (JavascriptExecutor)getDriver;
	        		  
	        		  element.click();
	        	      executor.executeScript("arguments[0].value = arguments[1];", element,Case_ID);
	        	      ReusableAsset.buttonClick("//*[@id='searchMenuButton']", getDriver);
	        	      Thread.sleep(1500);
	        	      
	        	      getDriver.switchTo().frame("_searchPanel_iframe");
	                	
	        	      List<WebElement> elements = getDriver.findElements(By.className("SearchResultsRuleNameHighlight"));
	        	      
	        	      for(WebElement element2 : elements){
	        	    	  System.out.println(element2.getText());
	        	    	  if(element2.getText().equals(Case_ID))
	        	    	  {
	        	    		  	JavascriptExecutor js=(JavascriptExecutor)getDriver; 
	        	  				js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element2);	        	  				
	        	  				Thread.sleep(500);
	        	  				js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element2); 
	        	  				((JavascriptExecutor)getDriver).executeScript("arguments[0].click();" , element2);
	        	  				break;
	        	    	  	}
	                  	}
	        	      getDriver.switchTo().defaultContent();
	        	      		return getDriver;	
	                }


}
