package com.qa.ninjatutorials.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.ninjaTutorials.utilities.Utilities;
import com.qa.ninjatutorials.base.TestBase;


public class LoginTest extends TestBase{

	public LoginTest() throws Exception {
		super();
		
	}

	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser(prop.getProperty("browserName"));
		
		driver.findElement(By.xpath("//span[text() = 'My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
	}
	
	@Test(priority = 1)
	public void loginWithValidCredentials() {
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(), "Login Failure");
	
	}
	
	@Test(priority = 1)
	public void loginWithInvalidCredentials() {
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateDateTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys("Selenium@1234");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualWarningMsg = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible")).getText();
		String expectedWarningMgs = "Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertTrue(actualWarningMsg.contains(expectedWarningMgs), "Expected warning msg is not displayed.");

	}
	
	@Test(priority = 3)
	public void loginWithInvalidEmailIdValidPassword() {
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateDateTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualWarningMsg = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible")).getText();
		String expectedWarningMgs = "Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertTrue(actualWarningMsg.contains(expectedWarningMgs), "Expected warning msg is not displayed.");


	}
	
	@Test(priority = 4)
	public void loginWithValidEmailIdInvalidPassword() {
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-password")).sendKeys("Selenium@1233");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualWarningMsg = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible")).getText();
		String expectedWarningMgs = "Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertTrue(actualWarningMsg.contains(expectedWarningMgs), "Expected warning msg is not displayed.");
	

	}
	
	@Test(priority = 5)
	public void loginWithNoCredentials() {
		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		
		String actualWarningMsg = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible")).getText();
		String expectedWarningMgs = "Warning: No match for E-Mail Address and/or Password.";
		
		Assert.assertTrue(actualWarningMsg.contains(expectedWarningMgs), "Expected warning msg is not displayed.");


	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

	

}
