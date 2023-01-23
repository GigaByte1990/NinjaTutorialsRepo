package com.qa.ninjatutorials.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.ninjatutorials.base.TestBase;

public class SearchTest extends TestBase {
	
	public SearchTest() throws Exception {
		super();
		
	}

	public static WebDriver driver;
	
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser(prop.getProperty("browserName"));
	}
	
	@Test(priority = 1)
	public void verifySearchValidProduct() {
		driver.findElement(By.cssSelector("input.form-control.input-lg")).sendKeys("Samsung");
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Samsung SyncMaster 941BW")).isDisplayed());
	}
	
	@Test(priority = 2)
	public void verifySearchInvalidProduct() {
		driver.findElement(By.cssSelector("input.form-control.input-lg")).sendKeys("Dell");
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		String warningMsg = driver.findElement(By.xpath("//p[contains(text() ,'There is no product that matches the search criteria.')]")).getText();
		Assert.assertEquals(warningMsg, "There is no product that matches the search criteria.", "Something went wrong");
		
	}
	
	@Test(priority = 3)
	public void verifySearchNoProduct() {
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		String warningNoProduct = driver.findElement(By.xpath("//p[contains(text() ,'There is no product that matches the search criteria.')]")).getText();
		Assert.assertEquals(warningNoProduct, "There is no product that matches the search criteria.", "Something went wrong");
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
