package com.framework.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ReportExcel {
	
	public  void createReportFile(
    		String rawFeatureName,
    		String nameOfScenario,
    		Collection<String> getTagName,
    		String getStatus
    	
    		 ) throws Exception
    		{
   
		String scrReportFolder = "_Run Report/ReportFolder_"
				+ new SimpleDateFormat("yyyy_MM_dd").format(
						Calendar.getInstance().getTime()).toString()+"/";
		boolean outputFolder = new File(scrReportFolder).mkdir();
		
		File reportFile = new File(scrReportFolder+"Report.xls");
    			
    			 String[] s1 = getTagName.toArray(new String[0]); //Collection to array 
    			  
    			    
    			    
    			    
    	
    			if (!reportFile.exists())
    			{
    				//System.out.println("Entering code 1");
    		
    				HSSFWorkbook wb = new HSSFWorkbook();
    				Sheet sheet1 = wb.createSheet("Report");
    				Row row1 = sheet1.createRow(0);
    				CreationHelper createHelper = wb.getCreationHelper();
    				setWidthOfCells(sheet1);
    				row1.createCell(0).setCellValue(createHelper.createRichTextString("Feature"));
    				row1.createCell(1).setCellValue(createHelper.createRichTextString("Scenario"));
    				row1.createCell(2).setCellValue(createHelper.createRichTextString("Test Execution Status"));
    				row1.createCell(3).setCellValue(createHelper.createRichTextString("TestCase ID"));
    				
    	
    				for (int i=0; i<4; i++)
    				{
    					setStyleForCell(row1.getCell(i),wb);
    				}
    	
    	
    				row1 = sheet1.createRow(1);
    				row1.createCell(0).setCellValue(createHelper.createRichTextString(rawFeatureName));
    				row1.createCell(1).setCellValue(createHelper.createRichTextString(nameOfScenario));
    				row1.createCell(2).setCellValue(createHelper.createRichTextString(getStatus.toUpperCase()));
    				row1.createCell(3).setCellValue(createHelper.createRichTextString(s1[0]));
    				
        	
                 	
     
    				for (int i=0; i<4; i++)
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
        	
    				row1.createCell(0).setCellValue(createHelper.createRichTextString(rawFeatureName));
    				row1.createCell(1).setCellValue(createHelper.createRichTextString(nameOfScenario));
    				row1.createCell(2).setCellValue(createHelper.createRichTextString(getStatus.toUpperCase()));
    				row1.createCell(3).setCellValue(createHelper.createRichTextString(s1[0]));
    				
        	
    				for (int i=0; i<4; i++)
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
        }else if(cellVal.equals("Feature") || cellVal.equals("Scenario") || cellVal.equals("Test Execution Status") || cellVal.equals("TestCase ID") )
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
    	sheet1.setColumnWidth(3, 8000);
    }
	
}
