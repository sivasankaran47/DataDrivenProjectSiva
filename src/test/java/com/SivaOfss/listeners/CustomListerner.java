package com.SivaOfss.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.SivaOfss.Base.TestBase;
import com.SivaOfss.utilit.TestUtilit;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class CustomListerner extends TestBase implements ITestListener {

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

	}

	public void onTestStart(ITestResult result) {
		
		String sheetName="Summary";
		int TotRow = excel.getRowCount(sheetName);
		int TotCol = excel.getColumnCount(sheetName);
		for(int rows=2;rows<=TotRow;rows++)
		{
			 String TSID = excel.getCellData(sheetName, "TSID", rows);
			 String RM = excel.getCellData(sheetName, "RunMode", rows);
			 //System.out.println(result.getName());
			 if(TSID.equals(result.getName()))
			 {
				 if(!RM.equals("Y"))
				 {
					 test.log(Status.SKIP, "RunModeDisabled");
					 rep.flush();
					 throw new SkipException("Skipping this exception");
					 
				 }
				 else
				 {
					 System.out.println("Runmode enabled");
				 }
			 }
			 else
			 {
				 System.out.println("Runmode enabled");
			 }
	    }

	}

	public void onTestSuccess(ITestResult result) {
		 test = rep.createTest(result.getName());
		 test.log(Status.INFO, result.getName());
		 test.pass("pass");
		 rep.flush();
	}

	public void onTestFailure(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtilit.screenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("<a target=\"_blank\" href=" + TestUtilit.screenShotName + ">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href=" + TestUtilit.screenShotName + "><img scr=" + TestUtilit.screenShotName
				+ " height =100 width=100	></img></a>");
		 test = rep.createTest(result.getName());
		 test.log(Status.INFO, result.getTestName());
		 test.fail("Fail");
		 
		 rep.flush();
	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

}
