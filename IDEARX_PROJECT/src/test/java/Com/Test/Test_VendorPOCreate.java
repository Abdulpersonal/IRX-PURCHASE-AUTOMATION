package Com.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Pageobject.Duplicatedatabase;
import Pageobject.Pocreate;
import Utility.Commonmethods;
import Pageobject.BROWSERSTARTUP;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test_VendorPOCreate extends BROWSERSTARTUP{

	@Test
public void purchase()  throws IOException, InterruptedException {
		
		WebDriver driver = setup();
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Commonmethods com=new Commonmethods();
		com.clickparentMenu(driver, "Purchase");
		com.clickchildMenu(driver, "Purchase Order");
		Thread.sleep(2000);
		Pageobject.Pocreate purchase=new Pocreate();
		purchase.Create_PO_items(driver);
		
	}
}
