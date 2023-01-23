package com.qa.ninjatutorials.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.ninjaTutorials.utilities.Utilities;

public class TestBase {
	
	public static WebDriver driver;
    public static Properties prop;
	
	public TestBase() throws Exception {
		prop = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\ninjaTutorials\\config\\config.properties");
		prop.load(ip);
	}
	
	
	public static WebDriver initializeBrowser(String browserName) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("FireFox")) {
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGELOAD_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
		
	}

}
