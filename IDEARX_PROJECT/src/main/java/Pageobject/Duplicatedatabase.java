package Pageobject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Duplicatedatabase {
	
	
	By quantitytextbox = By.cssSelector("input[id*=ext-comp-]");  //Quantity text box
	//By discounttexthover = By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-Discount']"); //Discount double click
	By discounttextclick= By.xpath("//div[@class='x-layer x-editor x-small-editor x-grid-editor'][3]/input"); //Discount text box click
	//By PO_save_button= By.xpath("//table[@id='save_po']//td[@class='x-btn-mc']");  //Po save button
	By Purchase_S_No= By.id("PoNO"); // po search No
	By select_GRN_Icon= By.xpath("//img[contains(@src,'/images/Prescribe.png')]");  // GRN icon
	By item_field_doubleclick= By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-ItemName']"); // itemname field double click
	By PO_No_search=By.id("poNoCombo");
	By PO_No_dropdownselect=By.xpath("//div[@class='x-combo-list-item']");

	
	
	public WebDriver  Backenddata(WebDriver driver) throws InterruptedException {
		
		// TODO Auto-generated method stub
		{

			// TODO Auto-generated method stub
			
			//final String JDBC_DRIVER="com.mysql.jdbc.driver";
			final String URL="jdbc:mysql://139.59.68.85:3306/irx_merge_sep_07";
			final String UN="IdearxQARead";
			final String p="IdearxRead@123";
			System.out.println("Started");

			
			Connection conn=null;
			Statement st=null;

			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				try {
					System.out.println("Driver connection completed");

				conn=DriverManager.getConnection(URL,UN,p);

				st=conn.createStatement();
				String sql="SELECT p.id,COUNT(pd.purchaseorder)as count \r\n"
						+ "FROM  `irx_purchase_order` AS p\r\n"
						+ "INNER JOIN `irx_po_detail` AS pd\r\n"
						+ "WHERE\r\n"
						+ "p.id=pd.purchaseorder\r\n"
						+ "AND  p.createdtime \r\n"
						+ "BETWEEN \r\n"
						+ "'2022-09-30 00:00:00' AND '2022-09-30 23:00:00'\r\n"
						+ "GROUP BY pd.purchaseorder";
				
				ResultSet executequery=st.executeQuery(sql);
				
				while(executequery.next()) {
					
					String po=executequery.getString("id");
					int lineitem=executequery.getInt("count");
					System.out.println(po+"="+lineitem);
					int i=0;
					
					Connection conn1=null;
					Statement st1=null;
					conn1=DriverManager.getConnection(URL,UN,p);
		
					st1=conn1.createStatement();

					String detail="SELECT "
							+ "PD.BUYERITEMNAME AS ITEM,\r\n"
							+ "PD.UNITPRICE AS UNITRATE,\r\n"
							+ "PD.QUANTITY AS NOS,\r\n"
							+ "PD.TAXVALUE AS TAXPERC,\r\n"
							+ "PD.TOTALAMOUNT AS ITEMAMOUNT\r\n"
							+ "FROM `irx_po_detail` AS PD \r\n"
							+ "WHERE PD.PURCHASEORDER="+po+"";
							
					
					ResultSet working=st1.executeQuery(detail);
					
					List<String> list=new ArrayList<String>(); 
					while(working.next()) {
					i=i+1;
					System.out.println("--------"+i);
					String itemname=working.getString("ITEM");
					String quantity=working.getString("NOS");
					String amount=working.getString("ITEMAMOUNT");
					
					list.add(itemname);
					list.add(quantity);

					
					 System.out.println("Po Number= ("+po+") and  itemname=  "+itemname+"");
					 Select_purchase(driver, itemname,quantity,i,lineitem); 
					 list.clear();
	
				}
					
					working.close();
					st1.close();
					conn1.close();
					System.out.println("---------------");
				}
				
				executequery.close();
				st.close();
				conn.close();
				System.out.println("--------end-----");
				
			}
				catch(SQLException e) {
				e.printStackTrace();	
			}
			
		}
			
			
			catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
			//return Name;

		}
		return driver;
	}
	
	
	
	public WebDriver Select_purchase(WebDriver driver,String itemname,String quantity,int i,int count) throws InterruptedException {
			
			String item="TAXIM 250MG INJ";
			Pageobject.Pocreate dep=new Pocreate();
			dep.Select_itemname(driver, item, i, quantity);
		
		  //Loop until final row in array
			 if(i!=count) {
			 driver.findElement(discounttextclick).sendKeys(Keys.ENTER);
	}
			 else {
				 
				 //driver.navigate().refresh();
			
				 //driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
				 System.out.println("PO Created successfully");
				 Thread.sleep(5000);
				 driver.findElement(By.xpath("//span[contains(text(),'Purchase')]")).click();
				 driver.findElement(By.xpath("//a[contains(text(),'Purchase Order')]")).click();
				 
			     dep.Select_dept(driver);					
			 }
			 
	System.out.println("Line item created");
		return driver;
	}
}
