package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Propertyreader {
	
	public static Properties loadSQLProperty() {
		
		Properties prop = new Properties();
		FileInputStream propfile = null;
		try {
			propfile = new FileInputStream
			(
				System.getProperty("user.dir")+"/src/main/java/Utility/Property.properties");
			// System.out.println(System.getProperty("user.dir") + "\\src\\main\\java\\Utility\\Property.properties");
				prop.load(propfile);
				prop.getProperty("url");
			} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return prop;
	}

}
