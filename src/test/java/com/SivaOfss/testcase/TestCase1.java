package com.SivaOfss.testcase;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.SivaOfss.Base.TestBase;

public class TestCase1 extends TestBase {

	@Test
	public void testcase() throws InterruptedException {

		click("bankMangerbtn");
		Thread.sleep(3000);
		
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))), "No ElementFound");
		driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
		//click("addCustBtn");
		// log.error("dsd");
	}
}
