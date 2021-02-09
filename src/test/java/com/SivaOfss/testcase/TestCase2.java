package com.SivaOfss.testcase;

import java.io.IOException;
import java.util.Hashtable;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.SivaOfss.Base.TestBase;
import com.SivaOfss.utilit.TestUtilit;

public class TestCase2 extends TestBase {

	@Test(dataProviderClass = TestUtilit.class, dataProvider = "dp")
	/**
	 * public void addCust(String FirstName, String LastName, String pincode, String
	 * pageAlert) throws InterruptedException, IOException
	 */
	public void addCust(Hashtable<String, String> data) throws InterruptedException, IOException {
		verifyEqual("abc", "zyx");
		click("addCustBtn");
		type("addCustFname", data.get("FirstName"));
		type("addCustLname", data.get("LastName"));
		// type("addCustPostCd",data.get("PinCode"));
	

		String pCode = data.get("PinCode");
		if (pCode.contains(".")) {
			String pincode = pCode.substring(0, pCode.lastIndexOf("."));
			type("addCustPostCd", pincode);
		}

		else {
			type("addCustPostCd", data.get("PinCode"));
		}

	

		click("custAddSubmit");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("AlertMsg")));
	
		alert.accept();

	}

}
