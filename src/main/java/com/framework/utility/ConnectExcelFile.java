package com.framework.utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;


public class ConnectExcelFile {
	
	public HSSFWorkbook createWorkBook() throws IOException{

	    //Create an object of File class to open xlsx file	
		File file =    new File("datasheet\\TestDataSPM.xls");
	    //Create an object of FileInputStream class to read excel file
	    FileInputStream inputStream = new FileInputStream(file);
	    HSSFWorkbook spmTestDataWorkBook_Read = null;
	    spmTestDataWorkBook_Read = new HSSFWorkbook(inputStream);	    
	    return spmTestDataWorkBook_Read;

	    }
	
	public String getUserName(HSSFWorkbook spmTestDataWorkBook_Read, String Operator) throws Exception{
		String userNameSPM = null;
		Sheet userCredentialSheet = spmTestDataWorkBook_Read.getSheet("UserCredentials");				
		int rowCount = userCredentialSheet.getLastRowNum()-userCredentialSheet.getFirstRowNum();	
	    //Create a loop over all the rows of excel file to read it

	    for (int i = 0; i < rowCount+1; i++) {

	    	String operatorValueExcel = userCredentialSheet.getRow(i).getCell(0).getStringCellValue();
	        if (operatorValueExcel.equals(Operator))
	        {
	        	userNameSPM=userCredentialSheet.getRow(i).getCell(1).getStringCellValue();
	        }
	      }
		return userNameSPM;
	}
	public String getPassword(HSSFWorkbook spmTestDataWorkBook_Read, String Operator) throws Exception{
		String userPasswordSPM = null;
		Sheet userCredentialSheet = spmTestDataWorkBook_Read.getSheet("UserCredentials");
				
		int rowCount = userCredentialSheet.getLastRowNum()-userCredentialSheet.getFirstRowNum();
	    //Create a loop over all the rows of excel file to read it

	    for (int i = 0; i < rowCount+1; i++) {
	    	String operatorValueExcel = userCredentialSheet.getRow(i).getCell(0).getStringCellValue();
	        if (operatorValueExcel.equals(Operator))
	        {
	        	userPasswordSPM=userCredentialSheet.getRow(i).getCell(2).getStringCellValue();
	        }
	      }		
		return userPasswordSPM;
	}
	public String getDataFromDataSheet(HSSFWorkbook spmTestDataWorkBook_Read, String testCaseId, String propertyId, String dataSheetName) throws Exception{
		String dataForProperty = null;
		Sheet currentTestDataSheet = spmTestDataWorkBook_Read.getSheet(dataSheetName);				
		int rowCount = currentTestDataSheet.getLastRowNum()-currentTestDataSheet.getFirstRowNum();		
	    //Create a loop over all the rows of excel file to read it
	    try
	    {
		for (int i = 0; i < rowCount+1; i++) {

	    	String testIdValueExcel = currentTestDataSheet.getRow(i).getCell(0).getStringCellValue();
	        if (testIdValueExcel.equals(testCaseId))
	        {
	        	
	        	int colCount = currentTestDataSheet.getRow(i).getLastCellNum();
	        	for (int j=0; j<colCount; j++){
	        		
	        		String propertyIdValueExcel = currentTestDataSheet.getRow(0).getCell(j).getStringCellValue();
	        		if (propertyIdValueExcel.equals(propertyId))
	        		{
	        			dataForProperty= currentTestDataSheet.getRow(i).getCell(j).getStringCellValue();
	        		}
	        	}
	        }	         		        
	       }
	    
	    }catch (Exception e)
	    {
	    	System.out.println(e);
	    }		
		return dataForProperty;
	}	
}
