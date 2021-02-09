package com.SivaOfss.testcase;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.SivaOfss.Base.TestBase;
import com.SivaOfss.utilit.TestUtilit;

public class TestCase3 extends TestBase {
	@Test(dataProviderClass = TestUtilit.class, dataProvider = "dp")
	public void openAcc(Hashtable<String , String> data) throws InterruptedException {
		click("openbnkbtn");
		select("CustNm", data.get("Customer"));
		select("CustCur", data.get("Curreny"));
		click("ProcBtn");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("AlertMsg")));
		alert.accept();
	}
}