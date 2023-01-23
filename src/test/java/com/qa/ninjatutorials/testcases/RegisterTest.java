package com.qa.ninjatutorials.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.ninjaTutorials.utilities.Utilities;
import com.qa.ninjatutorials.base.TestBase;

public class RegisterTest extends TestBase {
	
	public RegisterTest() throws Exception {
		super();
		
	}

	public static WebDriver driver;
	
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser(prop.getProperty("browserName"));
		driver.findElement(By.xpath("//span[text() = 'My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	
	@Test(priority = 1)
	public void verifyRegistrationWithMandatoryFields() {
		driver.findElement(By.id("input-firstname")).sendKeys("Selenium2");
		driver.findElement(By.id("input-lastname")).sendKeys("Panda2");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateDateTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("987654321");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium@123");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualHeading = driver.findElement(By.xpath("//div[@id = 'content']/h1")).getText();
		String expectedHeading = "Your Account Has Been Created!";
		
		Assert.assertTrue(actualHeading.contains(expectedHeading), "Arrr Errror");
		
	}
	
	@Test(priority = 2)
	public void verifyRegistrationWithAllFields() {
		driver.findElement(By.id("input-firstname")).sendKeys("Selenium1");
		driver.findElement(By.id("input-lastname")).sendKeys("Panda1");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateDateTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("987654321");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium@123");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@name = 'newsletter'][@value = '1']")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualHeading = driver.findElement(By.xpath("//div[@id = 'content']/h1")).getText();
		String expectedHeading = "Your Account Has Been Created!";
		
		Assert.assertTrue(actualHeading.contains(expectedHeading), "Arrr Errror");
	}
	
	@Test(priority = 3)
	public void verifyRegistrationWithNoDetails() {
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String warningMsg = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible")).getText();	
		Assert.assertEquals(warningMsg, "Warning: You must agree to the Privacy Policy!", "Something went wrong");
		
		String warningFirstName = driver.findElement(By.xpath("//div[contains(text() , 'First Name must be between 1 and 32 characters!')]")).getText();	
		Assert.assertEquals(warningFirstName, "First Name must be between 1 and 32 characters!", "Something went wrong");
		
		String warningLastName = driver.findElement(By.xpath("//div[contains(text() , 'Last Name must be between 1 and 32 characters!')]")).getText();	
		Assert.assertEquals(warningLastName, "Last Name must be between 1 and 32 characters!", "Something went wrong");
		
		String warningEmailId = driver.findElement(By.xpath("//div[contains(text() , 'E-Mail Address does not appear to be valid!')]")).getText();	
		Assert.assertEquals(warningEmailId, "E-Mail Address does not appear to be valid!", "Something went wrong");
		
		String warningTelephone = driver.findElement(By.xpath("//div[contains(text() , 'Telephone must be between 3 and 32 characters!')]")).getText();	
		Assert.assertEquals(warningTelephone, "Telephone must be between 3 and 32 characters!", "Something went wrong");
		
		String warningPassword = driver.findElement(By.xpath("//div[contains(text() , 'Password must be between 4 and 20 characters!')]")).getText();	
		Assert.assertEquals(warningPassword, "Password must be between 4 and 20 characters!", "Something went wrong");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
