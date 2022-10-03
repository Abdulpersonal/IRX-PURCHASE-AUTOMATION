package Pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BROWSERSTARTUP {
	
	public WebDriver setup() {
		
		WebDriverManager.chromedriver().setup();
		WebDriver  driver=new ChromeDriver();
		driver.get("http://3.81.197.170:8081/");
		driver.manage().window().maximize();
		driver.findElement(By.id("j_username")).sendKeys("ideamed");
		driver.findElement(By.id("j_password")).sendKeys("ideas2it");
		driver.findElement(By.id("btnSubmit")).click();
		return driver;
	}

}
