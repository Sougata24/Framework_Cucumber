package com.framework.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class onErrorScreenshot {
	
	
	public void takeOnErrorScreenshot(String methodName,String currClassName,WebDriver driver)
	{
		String scrFolder = "_OnErrorScreenShots/SCREENSHOTS_"+currClassName
				+ new SimpleDateFormat("yyyy_MM_dd").format(
						Calendar.getInstance().getTime()).toString()+"/";
		boolean outputFile = new File(scrFolder).mkdir();
		String filePath = scrFolder;
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with test method name 
           try {
           	LocalDate localDate = null;
				FileUtils.copyFile(scrFile, new File(filePath+methodName+"_"+localDate.now()+".png"));
				//System.out.println("***Placed screen shot in "+filePath+" ***");
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}

}
