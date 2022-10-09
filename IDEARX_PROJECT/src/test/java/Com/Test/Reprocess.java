package Com.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import Pageobject.BROWSERSTARTUP;
import Pageobject.Duplicatedatabase;
import Pageobject.Pocreate;

public class Reprocess extends BROWSERSTARTUP{
	
	@Test
	public void poreprocess() throws IOException, SQLException, InterruptedException
	{
		
		
		
		String path=System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(path+"\\Excel\\PoFail.xlsx");

		XSSFWorkbook workbook1=new XSSFWorkbook(fis);
		XSSFSheet prefixsheet = workbook1.getSheet("Sheet1");
		int rowcount=prefixsheet.getLastRowNum();
		 XSSFRow row = prefixsheet.getRow(rowcount);
		int colcount=row.getLastCellNum();
		
        WebDriver driver = setup();
        
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[contains(text(),'Purchase')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Purchase Order')]")).click();
		Thread.sleep(2000);
		Pageobject.Pocreate purchase=new Pocreate();
		purchase.Select_dept(driver);
		
		for(int j=0;j<rowcount;j++) {
			String processstate=prefixsheet.getRow(j+1).getCell(3).getStringCellValue();
			if(processstate.equals("fail")) {
				
			String poid=prefixsheet.getRow(j+1).getCell(1).getStringCellValue();
			Pageobject.Duplicatedatabase detail=new Duplicatedatabase();
			detail.podetailquery(driver, poid, colcount, j);
			XSSFRow row1 = prefixsheet.getRow(j);
			XSSFCell cell1 = row1.createCell(3);
			cell1.setCellValue("Success");
			}
		}
		
		
	}
}
