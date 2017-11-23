package com.framework.page;

import org.openqa.selenium.WebDriver;

import com.framework.utility.ConnectORproperties;
import com.framework.utility.ReusableAsset;

public class _ReqDetails {

	public static WebDriver createReq(WebDriver driver) throws Exception
	{
		ConnectORproperties conORPropObj = new ConnectORproperties();
		ReusableAsset.buttonClick(conORPropObj.getProperty("ReqDetails.createReqlink.xpath"), driver);	
		Thread.sleep(500);
		ReusableAsset.buttonClick(conORPropObj.getProperty("ReqDetails.createReqSPMButton.xpath"), driver);
		Thread.sleep(500);
		return driver;
	}
	public static WebDriver enterDetails(WebDriver driver) throws Exception
	{
		// Enter  Data for Request Details screen, Select ActivityType, Provide Service Provided.
		
		ConnectORproperties conORPropObj = new ConnectORproperties();
		driver.switchTo().frame("PegaGadget1Ifr");
		Thread.sleep(200);
		//HighlightElement.highLightElement( "//*[@id='Title']",driver);
		ReusableAsset.enterText(conORPropObj.getProperty("ReqDetails.title.xpath"), "SP Title Test", driver);
		ReusableAsset.enterText(conORPropObj.getProperty("ReqDetails.department.xpath"), "SPM Department", driver);
		ReusableAsset.enterText(conORPropObj.getProperty("ReqDetails.description.xpath"), "SPM Request Description Test", driver);
		ReusableAsset.enterText(conORPropObj.getProperty("ReqDetails.reference.xpath"), "www.google.com", driver);
		ReusableAsset.enterText(conORPropObj.getProperty("ReqDetails.serviceprovided.xpath"), "Test Service Provided", driver);
		ReusableAsset.buttonClick(conORPropObj.getProperty("ReqDetails.activitytype1.xpath"), driver);
		Thread.sleep(200);
		ReusableAsset.buttonClick(conORPropObj.getProperty("ReqDetails.activitytype2.xpath"), driver);
		Thread.sleep(200);
		driver.switchTo().defaultContent();
	//	ReusuableAsset.buttonClick(conORPropObj.getProperty("ReqDetails.submit.xpath"), driver);				
		return driver;
	}
	
}
