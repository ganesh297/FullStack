package com.selenium.bootcamp;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class ExcelReading {
	
	
	@Test
	public void TestDataFeed() throws IOException{

		// Create object array with 2 rows and 2 column- first parameter is row and second is //column
		

		
		String path = System.getProperty("user.dir");
		String FilePath = path + "\\TestData.xlsx";
		System.out.println(FilePath);
		FileInputStream fis = new FileInputStream(FilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
	      
		int rowTotal = sheet.getLastRowNum();
		int noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
		
		Object [][] data=new Object[rowTotal][noOfColumns];
		
		for(int i=1;i<=rowTotal;i++){
			
			for(int j=0;j<noOfColumns;j++){
			
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(j);
			   	//System.out.println(cell);
				System.out.println(sheet.getRow(i).getCell(j));
				data[i-1][j]=sheet.getRow(i).getCell(j);
				
			}
		}
		
		/*data[0][0]="mercury.bootcamp@testleaf.com";
		data[0][1]="Bootcamp@123";
		
		data[1][0]="username2@gmail.com";
		data[1][1]="Password2";*/
		
	//	return data;
	}

}
