package com.SivaOfss.Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.SivaOfss.utilit.ExcelReader;
import com.SivaOfss.utilit.ExtentManager;
import com.SivaOfss.utilit.TestUtilit;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestBase {

	public static WebDriver driver;
	static WebElement dropdown;
	public static Properties OR = new Properties();
	public static Properties config = new Properties();
	public static FileInputStream fis;
	public static Logger log = LogManager.getLogger(TestBase.class);
	public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\TestData.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	@BeforeSuite
	public void setUp() throws IOException {
	DOMConfigurator.configure("log4j.xml");
		if (driver == null) {
		fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
		config.load(fis);
		log.info("ConfigFileLoad");
		fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
		OR.load(fis);
		log.info("ORFileLoad");
		
	}
		if(config.getProperty("browser").equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\executables\\chromedriver.exe");
			driver= new ChromeDriver();
		}
		driver.get(config.getProperty("TestUrl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver,10);
	}
	
	public void click(String locator)
	{
		driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
	
		
	}
	
	public void type(String locator, String Value )
	{
		driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(Value);
	   // test.log(Status.INFO, "typing on"+locator + "value "+ Value);
	}
	public boolean isElementPresent(By by)
	{
		try {
		driver.findElement(by);
		return true;
		}
		catch(NoSuchElementException e)
		{
			return false;
		}
	}
	
	public static void verifyEqual(String expected,String actual) throws IOException
	{
		
		try
		{
			Assert.assertEquals(actual, expected);
		}
		catch(Throwable e)
		{
			TestUtilit.screenshot();
			Reporter.log("<a target=\"_blank\" href=" + TestUtilit.screenShotName + "><img scr=" + TestUtilit.screenShotName
					+ " height =100 width=100	></img></a>");
			Reporter.log("<br>");
		}
	}
	
	public static void select(String locator,String value)
	{
		dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
	}
	@AfterSuite
	public void tearDown() {
		driver.quit();
		log.info("success");
	}
}
