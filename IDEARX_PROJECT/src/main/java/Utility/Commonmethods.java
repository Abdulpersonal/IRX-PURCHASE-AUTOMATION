package Utility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Commonmethods {
	
	
	public WebDriver clickchildMenu(WebDriver driver,String childmenuname) 
	{
		driver.findElement(By.xpath("//a[contains(text(),'"+childmenuname+"')]")).click();
		return driver;
	}
	
	
	//Clicking parent menu screen
	public WebDriver clickparentMenu(WebDriver driver,String menuname) 
	{
		driver.findElement(By.xpath("//span[contains(text(),'"+menuname+"')]")).click();
		return driver;
	}
	
	public WebDriver Call_Explicitwait(WebDriver driver,By locatorname) 
	{
		WebDriverWait wait1= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.elementToBeClickable(driver.findElement(locatorname)));
		return driver;
	}
	
	
	public WebDriver click(WebDriver driver,By deptselect) 
	{
		driver.findElement(deptselect).click();
		return driver;
		
	}
	
	public WebDriver implicitwait(WebDriver driver) 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
		
	}
	public WebDriver sendkeys(WebDriver driver,By element,String enter) 
	{
		driver.findElement(element).sendKeys(enter);
		return driver;
		
	}
	
	public WebDriver keyboardpress(WebDriver driver,By element,Keys input) 
	{
		driver.findElement(element).sendKeys(input);
		return driver;
		
	}
	

}
