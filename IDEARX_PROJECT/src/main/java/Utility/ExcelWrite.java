package Utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import com.google.common.collect.Table.Cell;
import com.mysql.cj.result.Row;

public class ExcelWrite {
	
	
	

	public WebDriver write(WebDriver driver,String po,String status,String itemname,String d) throws IOException {
		
		String path=System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(path+"\\Excel\\PoFail.xlsx");

		XSSFWorkbook workbook1=new XSSFWorkbook(fis);
		XSSFSheet prefixsheet = workbook1.getSheet("Sheet1");
		
		//String s=prefixsheet.getRow(0).getCell(0).getStringCellValue();
		int rowcount=prefixsheet.getLastRowNum();
		 XSSFRow row = prefixsheet.createRow(rowcount+1);
		 int colcount=row.getLastCellNum();
		
		 XSSFCell cell = row.createCell(0);
			cell.setCellValue(po);
			XSSFCell cell1 = row.createCell(1);
			cell1.setCellValue(itemname);
			XSSFCell cell2 = row.createCell(2);
			cell1.setCellValue(d);
			XSSFCell cell3 = row.createCell(3);
			cell1.setCellValue(status);
			
		FileOutputStream fos = new FileOutputStream(path+"\\Excel\\PoFail.xlsx");

		workbook1.write(fos);
		fos.close();
		return driver;
	}
			
			
}
