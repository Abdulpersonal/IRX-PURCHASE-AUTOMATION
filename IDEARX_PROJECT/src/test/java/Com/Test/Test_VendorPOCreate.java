package Com.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import Pageobject.Duplicatedatabase;
import Pageobject.Pocreate;
import Pageobject.BROWSERSTARTUP;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test_VendorPOCreate extends BROWSERSTARTUP{

	@Test
public void purchase()  throws IOException, InterruptedException {
		
		WebDriver driver = setup();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//span[contains(text(),'Purchase')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Purchase Order')]")).click();
		Thread.sleep(2000);
		Pageobject.Pocreate purchase=new Pocreate();
		purchase.Create_PO_items(driver);	
		
	}
}
