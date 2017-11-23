package com.framework.utility;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.parent.BaseClass;


public class UtilityClass extends BaseClass  implements ITestListener {


		String scrFolder = "_OnErrorScreenShots/SCREENSHOTS_"
				+ new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(
						Calendar.getInstance().getTime()).toString()+"/";
		boolean outputFile = new File(scrFolder).mkdir();
    
		String filePath = scrFolder;   
		static String scrReportFolder = "_Run Report/ReportFolder_"
				+ new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(
						Calendar.getInstance().getTime()).toString()+"/";
		boolean outputFolder = new File(scrReportFolder).mkdir();
    
 
		@Override
		public void onTestFailure(ITestResult result) {
    	
			String methodName=result.getName().toString().trim();
			takeScreenShotOnErr(methodName, driver);
    	
			try {
				String getTestName = result.getName();
				String getTestStatus= "FAILED";
				String getError= result.getThrowable().getMessage();
				long startTime = result.getStartMillis();
				long endTime = result.getEndMillis(); 
				String getTestDesc = result.getMethod().getDescription();	
				String timeElapsed = String.valueOf((endTime - startTime)/1000);
    		
			createReportFile(
					 getTestName,
					 getTestStatus,
					 getError,
					 timeElapsed,
					 getTestDesc);
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
    
    public void takeScreenShotOnErr(String methodName, WebDriver driver) {
    	//get the driver
    	
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
	public void onFinish(ITestContext context) {}
  
    public void onTestStart(ITestResult result) {   }
  
    @Override
    public void onTestSuccess(ITestResult result) { 
    	
    	try {
    		String getTestName = result.getName();
    		String getTestStatus= "PASSED";
    		String getError= "NA";
    		long startTime = result.getStartMillis();
    		long endTime = result.getEndMillis(); 
    		String getTestDesc = result.getMethod().getDescription();	
    		String timeElapsed = String.valueOf((endTime - startTime)/1000);
    		
    	
			createReportFile(
					 getTestName,
					 getTestStatus,
					 getError,
					 timeElapsed,
					 getTestDesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void onTestSkipped(ITestResult result) {   }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {   }

    public void onStart(ITestContext context) { }
    
    public static void createReportFile(
    		String getTestName,
    		String getTestStatus,
    		String getError,
    		String timeElapsed,
    		String getTestDesc ) throws Exception
    		{
    			File reportFile = new File(scrReportFolder+"Report.xls");
    	
    			if (!reportFile.exists())
    			{
    				//System.out.println("Entering code 1");
    		
    				HSSFWorkbook wb = new HSSFWorkbook();
    				Sheet sheet1 = wb.createSheet("Report");
    				Row row1 = sheet1.createRow(0);
    				CreationHelper createHelper = wb.getCreationHelper();
    				setWidthOfCells(sheet1);
    				row1.createCell(0).setCellValue(createHelper.createRichTextString("Test Name"));
    				row1.createCell(1).setCellValue(createHelper.createRichTextString("Test Description"));
    				row1.createCell(2).setCellValue(createHelper.createRichTextString("Test Execution Status"));
    				row1.createCell(3).setCellValue(createHelper.createRichTextString("Time Elapsed"));
    				row1.createCell(4).setCellValue(createHelper.createRichTextString("Error"));	
    	
    				for (int i=0; i<5; i++)
    				{
    					setStyleForCell(row1.getCell(i),wb);
    				}
    	
    	
    				row1 = sheet1.createRow(1);
    				row1.createCell(0).setCellValue(createHelper.createRichTextString(getTestName));
    				row1.createCell(1).setCellValue(createHelper.createRichTextString(getTestDesc));
    				row1.createCell(2).setCellValue(createHelper.createRichTextString(getTestStatus));
    				row1.createCell(3).setCellValue(createHelper.createRichTextString(timeElapsed));
    				row1.createCell(4).setCellValue(createHelper.createRichTextString(getError));
        	
                 	
     
    				for (int i=0; i<5; i++)
    				{
    					setStyleForCell(row1.getCell(i),wb);
    				}
        	
    				FileOutputStream fileOut = new FileOutputStream(scrReportFolder+"Report.xls");
        	
    				wb.write(fileOut);
    				wb.close();
    				fileOut.close();
    			}
    			else
    			{
    				//System.out.println("Entering code 2");
    		
    				FileInputStream inputStream = null;        	
    				inputStream = new FileInputStream(new File(scrReportFolder+"Report.xls"));
    				HSSFWorkbook wb = new HSSFWorkbook(inputStream);
    				Sheet sheet1 = wb.getSheet("Report");
    				int rowCount=sheet1.getLastRowNum()+1;
    				setWidthOfCells(sheet1);
        
    				Row row1 = sheet1.createRow(rowCount);
        	
        /*	 System.out.println("Test: " + cell.getStringCellValue());*/
        	 
    				CreationHelper createHelper = wb.getCreationHelper();
        	
    				row1.createCell(0).setCellValue(createHelper.createRichTextString(getTestName));
    				row1.createCell(1).setCellValue(createHelper.createRichTextString(getTestDesc));
    				row1.createCell(2).setCellValue(createHelper.createRichTextString(getTestStatus));
    				row1.createCell(3).setCellValue(createHelper.createRichTextString(timeElapsed));
    				row1.createCell(4).setCellValue(createHelper.createRichTextString(getError));
        	
    				for (int i=0; i<5; i++)
    				{
    					setStyleForCell(row1.getCell(i),wb);
    				}
    				inputStream.close();
        	
    				FileOutputStream fileOut = new FileOutputStream(scrReportFolder+"Report.xls");
    				wb.write(fileOut);
    				wb.close();
    				fileOut.close();
    			}
    }
    public static void setStyleForCell(Cell cell, Workbook wb)
    {
    	CellStyle style = wb.createCellStyle();
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        
        String cellVal=cell.getStringCellValue();
       System.out.println(cellVal);
        if (cellVal.equals("PASSED"))
        {
        	//System.out.println(cellVal);
        	style.setFillForegroundColor(HSSFColor.GREEN.index);
        }else if (cellVal.equals("FAILED"))
        {
        	//System.out.println(cellVal);
        	style.setFillForegroundColor(HSSFColor.RED.index);
        }else if(cellVal.equals("Test Name") || cellVal.equals("Test Description") || cellVal.equals("Test Execution Status") || cellVal.equals("Time Elapsed") || cellVal.equals("Error"))
        {
        	style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        }
        else
        {
        	style.setFillForegroundColor(HSSFColor.WHITE.index);
        }
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.getShrinkToFit();
        
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setWrapText(true);
        cell.setCellStyle(style);
        
        
        HSSFFont font = (HSSFFont) wb.createFont();
        font.setColor(HSSFColor.DARK_BLUE.index);
        style.setFont(font);
    }
    public static void setWidthOfCells(Sheet sheet1)
    {
    	sheet1.setColumnWidth(0, 8000);
    	sheet1.setColumnWidth(1, 8000);
    	sheet1.setColumnWidth(4, 15000);
    }
	
}
