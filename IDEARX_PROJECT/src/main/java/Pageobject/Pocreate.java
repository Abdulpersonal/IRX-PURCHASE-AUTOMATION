package Pageobject;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.Commonmethods;
import Utility.ExcelWrite;


public class Pocreate 
{

	By quantitytextbox = By.cssSelector("input[id*=ext-comp-]");  //Quantity text box
	//By discounttexthover = By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-Discount']"); //Discount double click
	By discounttextclick= By.xpath("//div[@class='x-layer x-editor x-small-editor x-grid-editor'][3]/input"); //Discount text box click
	//By PO_save_button= By.xpath("//table[@id='save_po']//td[@class='x-btn-mc']");  //Po save button
	By Purchase_S_No= By.id("PoNO"); // po search No
	By select_GRN_Icon= By.xpath("//img[contains(@src,'/images/Prescribe.png')]");  // GRN icon
	By item_field_doubleclick= By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-ItemName']"); // itemname field double click
	By PO_No_search=By.id("poNoCombo");
	By PO_No_dropdownselect=By.xpath("//div[@class='x-combo-list-item']");
	By Deptselect=By.id("deptId");
	By dept=By.xpath("//input[contains(@id,'deptId')]/parent::div/img");
	By supplier=By.xpath("//input[contains(@id,'goodsSupplier')]/parent::div/img");
	By poitemtextbox=By.id("PO_GRIDItemNameEditor");
	By itemselectvalue=By.xpath("//div[contains(@id,'NameListItem')]");
	By duplicateitem=By.xpath("//span[contains(text(),'Item already Exists')]");
	By alertbox=By.xpath("//button[contains(text(),'Yes')]");
	By sup=By.id("goodsSupplier");
	
	
	
	Commonmethods com=new Commonmethods();
	
	
	//Create purchase order from po screen 
	public WebDriver Create_PO_items(WebDriver driver) throws InterruptedException, IOException 
	{
		Select_dept(driver);
		Pageobject.Duplicatedatabase vs=new Duplicatedatabase();
		vs.Backenddata(driver);
		driver.close();
		return driver;
		
		
		//String Po_NUMBER=driver.findElement(Purchase_S_No).getText();   //Get PO No
		//System.out.println(Po_NUMBER);	
	}
	
	
	//itemmapping
	public int Select_itemname(WebDriver driver,String itemname,int i,String quantity,String po,int count) throws InterruptedException, IOException 
	{
		
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.doubleClick(driver.findElement(By.xpath("//div[contains(@class,'x-grid3-row')]["+i+"]//div[@class='x-grid3-cell-inner x-grid3-col-ItemName']"))).perform();
		com.sendkeys(driver, poitemtextbox, itemname);
		Thread.sleep(7000);
		com.Call_Explicitwait(driver, itemselectvalue);
		String d=driver.findElement(itemselectvalue).getText();
		System.out.println("HMS ITEMNAME = "+d);
		
		if(itemname.equals(d)) 
		{
			com.click(driver, itemselectvalue);
			Thread.sleep(1000);
			com.sendkeys(driver, quantitytextbox, quantity);
			Actions action2 = new Actions(driver);
			action2.doubleClick(driver.findElement(By.xpath("//div[contains(@class,'x-grid3-row')]["+i+"]//div[@class='x-grid3-cell-inner x-grid3-col-Discount']"))).perform();
			Thread.sleep(1000);
		}
		else 
		{
			i=count;
			String status="fail";
			Utility.ExcelWrite set=new ExcelWrite();
			set.write(driver, po,status,itemname,d);
			System.out.println(po+" ==Failed");
			System.out.println(po+ " = Purchase order not integrated");		
		}
		
	return i;
	}
	
//department and supplier select
	public WebDriver Select_dept(WebDriver driver) throws InterruptedException 
	{
		com.Call_Explicitwait(driver, Deptselect);
		com.sendkeys(driver, Deptselect, "PHARMACY STORES");
		Thread.sleep(6000);
		com.click(driver, Deptselect);
		com.click(driver, supplier);
		WebElement consultant_click=driver.findElement(sup);
		Thread.sleep(2000);
		consultant_click.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
		return driver;
	
	}
}








//try 
//{		
//	WebElement duplicate_item=driver.findElement(duplicateitem);
//	System.out.println(duplicate_item.getText());
//	String v=duplicate_item.getText();
//	String B="Item already Exists";
//
//	if(v.equals(B)) 
//	{
//		System.out.println("Inside if loop");
//		com.Call_Explicitwait(driver, alertbox);
//		driver.findElement(alertbox).click();
//	}
//}
//catch(Exception e) 
//{
//	System.out.println(e);		
//}
