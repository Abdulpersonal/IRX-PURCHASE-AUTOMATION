package Pageobject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


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
	By Deptselect=By.xpath("//div[contains(text(),'IP Pharmacy')]");
	By dept=By.xpath("//input[contains(@id,'deptId')]/parent::div/img");
	By supplier=By.xpath("//input[contains(@id,'goodsSupplier')]/parent::div/img");

	
	
	
	
	//Create purchase order from po screen 
	public WebDriver Create_PO_items(WebDriver driver) throws InterruptedException {
		
		Select_dept(driver);
		Pageobject.Duplicatedatabase vs=new Duplicatedatabase();
		vs.Backenddata(driver);
	
		return driver;
		//String Po_NUMBER=driver.findElement(Purchase_S_No).getText();   //Get PO No
		//System.out.println(Po_NUMBER);
	
		
	}
	
	
	
	public WebDriver Select_itemname(WebDriver driver,String itemname,int i,String quantity) throws InterruptedException {
		Thread.sleep(2000);
		Actions action = new Actions(driver);
		action.doubleClick(driver.findElement(By.xpath("//div[contains(@class,'x-grid3-row')]["+i+"]//div[@class='x-grid3-cell-inner x-grid3-col-ItemName']"))).perform();
		driver.findElement(By.xpath("//input[contains(@id,'ItemNameEditor')]")).sendKeys(itemname);
		Thread.sleep(2000);
		WebElement itemlist=driver.findElement(By.xpath("//div[contains(@id,'NameListItem')]"));
		Thread.sleep(2000);
		itemlist.click();
	
		
		Thread.sleep(2000);
		driver.findElement(quantitytextbox).sendKeys(quantity);
		 Actions action2 = new Actions(driver);
			action2.doubleClick(driver.findElement(By.xpath("//div[contains(@class,'x-grid3-row')]["+i+"]//div[@class='x-grid3-cell-inner x-grid3-col-Discount']"))).perform();
			Thread.sleep(2000);
		
		return driver;
	}
	
	
	public WebDriver Select_dept(WebDriver driver) throws InterruptedException {
		
	Thread.sleep(3000);
	WebElement drop=driver.findElement(dept);
	drop.click();
	//Thread.sleep(2000);
	driver.findElement(Deptselect).click();
	Thread.sleep(3000);
	WebElement drop1=driver.findElement(supplier);
	drop1.click();
	WebElement consultant_click=driver.findElement(By.id("goodsSupplier"));
	Thread.sleep(2000);
	consultant_click.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
	return driver;
	
	}
}
