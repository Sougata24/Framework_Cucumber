package com.framework.page;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;

import com.framework.utility.ConnectExcelFile;
import com.framework.utility.ConnectORproperties;
import com.framework.utility.ReusableAsset;

public class _Login_Home {
	
	public static WebDriver loginApp(WebDriver driver, String loggedInOperator) throws Exception
	{
		ConnectExcelFile objConExlFile = new ConnectExcelFile();					
		
		HSSFWorkbook spmTestDataWorkBook_Read =objConExlFile.createWorkBook() ;
		String userNameSPM= objConExlFile.getUserName(spmTestDataWorkBook_Read , loggedInOperator);
		String userPasswordSPM= objConExlFile.getPassword(spmTestDataWorkBook_Read , loggedInOperator);
		
		ConnectORproperties conORPropObj = new ConnectORproperties();
			
		ReusableAsset.enterText(conORPropObj.getProperty("login.userName.xpath"), userNameSPM, driver);
		ReusableAsset.enterText(conORPropObj.getProperty("login.password.xpath"), userPasswordSPM, driver);
		
		ReusableAsset.buttonClick(conORPropObj.getProperty("login.submit.xpath"), driver);
		
		return driver;	
	}

}
