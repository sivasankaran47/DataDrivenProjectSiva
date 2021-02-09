package com.SivaOfss.utilit;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.aventstack.extentreports.reporter.configuration.ViewOrder;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports getInstance()
	{
		
		if(extent==null)
		{
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\Spark.html");	
			spark.config().setTheme(Theme.DARK);
			spark.config().setDocumentTitle("MyReport");
			extent.attachReporter(spark);
		}
		return extent;
		
	}

}
