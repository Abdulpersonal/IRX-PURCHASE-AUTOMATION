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

import Utility.ExcelWrite;


public class Pocreate {


	
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

	
	
	
	
	//Create purchase order from po screen 
	public WebDriver Create_PO_items(WebDriver driver) throws InterruptedException, IOException {
		
		Select_dept(driver);
		Pageobject.Duplicatedatabase vs=new Duplicatedatabase();
		vs.Backenddata(driver);
	
		return driver;
		//String Po_NUMBER=driver.findElement(Purchase_S_No).getText();   //Get PO No
		//System.out.println(Po_NUMBER);
	
		
	}
	
	
	
	public int Select_itemname(WebDriver driver,String itemname,int i,String quantity,String po,int count) throws InterruptedException, IOException {
		

		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.doubleClick(driver.findElement(By.xpath("//div[contains(@class,'x-grid3-row')]["+i+"]//div[@class='x-grid3-cell-inner x-grid3-col-ItemName']"))).perform();

		
		driver.findElement(By.id("PO_GRIDItemNameEditor")).sendKeys(itemname);
		Thread.sleep(10000);
		
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions . elementToBeClickable (By.id("PO_GRIDItem NameListItem")));
		String d=driver.findElement(By.xpath("//div[contains(@id,'NameListItem')]")).getText();
		System.out.println(d);
		
		
		if(itemname.equals(d)) {
		driver.findElement(By.xpath("//div[contains(@id,'NameListItem')]")).click();
		
		
		
		try {		
	WebElement duplicate_item=driver.findElement(By.xpath("//span[contains(text(),'Item already Exists')]"));
		System.out.println(duplicate_item.getText());
		String v=duplicate_item.getText();
		String B="Item already Exists";
		
	if(v.equals(B)) {
		System.out.println("Inside if loop");
		WebDriverWait wait2= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait2.until(ExpectedConditions . elementToBeClickable (By.xpath("//button[contains(text(),'Yes')]")));
			driver.findElement(By.xpath("//button[contains(text(),'Yes')]")).click();
		}
		}
		
		catch(Exception e) {
			System.out.println("single po line entry ");
			
		}
	
		
		Thread.sleep(2000);
		driver.findElement(quantitytextbox).sendKeys(quantity);
		 Actions action2 = new Actions(driver);
			action2.doubleClick(driver.findElement(By.xpath("//div[contains(@class,'x-grid3-row')]["+i+"]//div[@class='x-grid3-cell-inner x-grid3-col-Discount']"))).perform();
			Thread.sleep(2000);
	}
		
		else {
			i=count;
			String status="fail";
			Utility.ExcelWrite set=new ExcelWrite();
			set.write(driver, po,status,itemname,d);
			driver.findElement(By.id("PO_GRIDItemNameEditor")).clear();
			
			System.out.println(po+"==Failed");
			System.out.println(po+ "Purchase order not  integrated");
			
		}
		
		
		
		
		return i;
	}
	
	
	public WebDriver Select_dept(WebDriver driver) throws InterruptedException {
	
	WebDriverWait wait1= new WebDriverWait(driver,Duration.ofSeconds(5));
	wait1.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("deptId"))));
	driver.findElement(Deptselect).sendKeys("PHARMACY STORES");
	Thread.sleep(6000);
	driver.findElement(Deptselect).click();
	WebElement drop1=driver.findElement(supplier);
	drop1.click();
	WebElement consultant_click=driver.findElement(By.id("goodsSupplier"));
	Thread.sleep(2000);
	consultant_click.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
	return driver;
	
	}
}
