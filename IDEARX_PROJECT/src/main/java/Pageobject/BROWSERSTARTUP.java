package Pageobject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.Propertyreader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BROWSERSTARTUP {
	
	public WebDriver setup() {
		
		
		//ChromeOptions opt = new ChromeOptions();
		//opt.addArguments("--headless");
		
		
		
		
		Properties prop = new Properties();
		FileInputStream file;
		try 
		{
			file = new FileInputStream("C:\\Users\\Admin\\git\\IRX-PURCHASE-AUTOMATION\\IDEARX_PROJECT\\src\\main\\java\\Utility\\Property");
			prop.load(file);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebDriverManager.chromedriver().setup();
		//Properties prop = Propertyreader.loadSQLProperty();
		//String url = prop.getProperty("url");
		WebDriver driver = new ChromeDriver();
		String url=prop.getProperty("url");
		driver.get(url);
		driver.manage().window().maximize();
		String username=prop.getProperty("username");
		String password=prop.getProperty("password");
		driver.findElement(By.id("j_username")).sendKeys(username);
		driver.findElement(By.id("j_password")).sendKeys(password);
		driver.findElement(By.id("btnSubmit")).click();
		return driver;
	}
		
		
	}
	

