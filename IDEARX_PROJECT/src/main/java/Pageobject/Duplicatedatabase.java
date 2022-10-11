package Pageobject;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.Commonmethods;
import Utility.ExcelWrite;
import Utility.Propertyreader;
import io.github.bonigarcia.wdm.WebDriverManager;


public class Duplicatedatabase  
{
	
	public static int i;
	public static int k;
	public static Connection conn1;
	public static Statement st1;
	public static ResultSet working;
	
	
	
	

	
//	Properties P = Propertyreader.loadSQLProperty();
//	String url=P.getProperty("idearx_db_url");
//	String user=P.getProperty("idearx_db_username");
//	String password=P.getProperty("idearx_db_password");
//	final String URL=url;
//	final String UN=user;
//	final String p=password;

	
	By discounttextclick= By.xpath("//div[@class='x-layer x-editor x-small-editor x-grid-editor'][3]/input"); //Discount text box click
	By quantitytextbox = By.cssSelector("input[id*=ext-comp-]");  //Quantity text box
	By parentmeu=By.xpath("//span[contains(text(),'Purchase')]");
	By child=By.xpath("//a[contains(text(),'Purchase Order')]");
	By save=By.xpath("//button[contains(text(),'Save')]");
	
	
	Commonmethods com=new Commonmethods();
	final String URL="jdbc:mysql://139.59.68.85:3306/irx_merge_sep_07";
	final String UN="IdearxQARead";
	final String p="IdearxRead@123";
	

	public WebDriver Backenddata(WebDriver driver) throws InterruptedException, IOException
	{
		
		Connection conn=null;
		Statement st=null;
		
		
		System.out.println("Started");
			

		try 
		{
				
			Class.forName("com.mysql.jdbc.Driver");
			try 
			{
				System.out.println("Driver connection completed");
				conn=DriverManager.getConnection(URL,UN,p);
				String path=System.getProperty("user.dir");
				XSSFWorkbook workbook1=new XSSFWorkbook(path+"\\Excel\\Success_po.xlsx");
				XSSFSheet prefixsheet = workbook1.getSheet("Sheet1");
				String ponumber=prefixsheet.getRow(1).getCell(0).getStringCellValue();
                System.out.println("Starting po number="+ponumber);
                
				st=conn.createStatement();
				String sql="SELECT p.id,COUNT(pd.purchaseorder)AS COUNT \r\n"
						+ "FROM  `irx_purchase_order` AS p\r\n"
						+ "INNER JOIN `irx_po_detail` AS pd\r\n"
						+ "WHERE\r\n"
						+ "p.id=pd.purchaseorder\r\n"
						+ "AND p.id > "+ponumber+"\r\n"
						+ "AND  p.createdtime >=CURDATE()\r\n"
						+"GROUP BY pd.purchaseorder\r\n"
						+ "ORDER BY p.id";
				
				ResultSet executequery=st.executeQuery(sql);
				
				while(executequery.next()) 
				{
					String po=executequery.getString("id");
					int lineitem=executequery.getInt("count");
					System.out.println(po+"Purchase order detail count = "+lineitem);
					i=0;
					podetailquery(driver, po,i,lineitem);
				}	
				
				executequery.close();
				st.close();
				conn.close();
				System.out.println("--------PURCHASE ORDER INTEGRATION COMPLETED-----");
				
			}
			catch(SQLException e) 
			{
				e.printStackTrace();	
			}
			
		}
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
			
	return driver;
	}
	
	
	
	
	public WebDriver podetailquery(WebDriver driver,String po,int i,int lineitem) throws SQLException, InterruptedException, IOException 
	{
		
		conn1=null;
		Statement st1=null;
		conn1=DriverManager.getConnection(URL,UN,p);
		st1=conn1.createStatement();
		
		String detail="SELECT\r\n"
				+ "IFNULL(pd.`BUYERITEMNAME`,pd.`SUPPLIERITEMNAME`) AS itemName,\r\n"
				+ "PD.UNITPRICE AS UNITRATE,\r\n"
				+ "PD.QUANTITY AS NOS,\r\n"
				+ "PD.TAXVALUE AS TAXPERC,\r\n"
				+ "PD.TOTALAMOUNT AS ITEMAMOUNT\r\n"
				+ "FROM\r\n"
				+ "`irx_po_detail` AS pd\r\n"
				+ "WHERE\r\n"
				+ "`PURCHASEORDER`="+po+"";
				
		
		ResultSet working=st1.executeQuery(detail);
		
		while(working.next()) 
		{
			if(i==lineitem) 
			{
				System.out.println(" Loop ended Next po need to be executed");
				break;
			}
			i=i+1;
			System.out.println("PO Detail line entry execution"+i);
			String itemname=working.getString("itemName");
			String quantity=working.getString("NOS");
			String amount=working.getString("ITEMAMOUNT");
			System.out.println("Po Number= ("+po+") and  Idearx itemname=  "+itemname+"");
			i=Select_purchase(driver, itemname,quantity,i,lineitem,po); 
		}
		
		working.close();
		st1.close();
		conn1.close();
		System.out.println("Purchase order created successfully");
		return driver;
		
	}
	
	
	public int Select_purchase(WebDriver driver,String itemname,String quantity,int i,int count,String po) throws InterruptedException, SQLException, IOException 
	{
		
		Pageobject.Pocreate dep=new Pocreate();
		i=dep.Select_itemname(driver, itemname, i, quantity,po,count);
		
		  //Loop until final row in array
			
		if(i!=count) 
		{	
			com.keyboardpress(driver, discounttextclick, Keys.ENTER);		
		}
		
		else 
			
		{
			com.click(driver, save);
			Utility.ExcelWrite set=new ExcelWrite();
			set.success_po(driver, po);
			Thread.sleep(1000);
			driver.navigate().refresh();
			com.implicitwait(driver);
			com.Call_Explicitwait(driver, parentmeu);
			com.clickparentMenu(driver, "Purchase");
			Thread.sleep(2000);
			com.clickchildMenu(driver, "Purchase Order");
			//driver.findElement(child).click();
			com.implicitwait(driver);
			dep.Select_dept(driver);					
		}
			 
		System.out.println("PO Line item created");
		return i;
	}
	
}
