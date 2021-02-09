package com.SivaOfss.other;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SivaOfss.utilit.ExcelReader;


public class roughClass {
	
	public static final Logger log = LogManager.getLogger(roughClass.class);
	public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\TestData.xlsx");
	//Method m
	public Object[][] getData()
	//public void getData()
	{
		String sheetName="Summary";
		int TotRow = excel.getRowCount(sheetName);
		int TotCol = excel.getColumnCount(sheetName);
		System.out.println(TotRow);
		System.out.println(TotCol);
		Object[][] data=new Object[TotRow-1][TotCol];
		for(int rows=2;rows<=TotRow;rows++)
		{
			System.out.println(excel.getCellData(sheetName, "RunMode", rows));
			System.out.println("before iterate");
		//	for(int cols=0;cols<TotCol;cols++)
		//	{
				//System.out.println(sheetName);
			   // System.out.println(cols);
				//System.out.println(rows);
		//		System.out.println(excel.getCellData(sheetName, "RunMode", rows));
				//data[rows-2][cols]=excel.getCellData(sheetName, cols, rows);
				//System.out.println(excel.getCellData(sheetName, cols, rows));
		//	}
	//	return null;
	}
		
		return data;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\SIVANAT\\Desktop\\Selenium_Learning\\MavenProject\\src\\test\\resources\\properties\\config.properties");
		prop.load(fis);
		String pd = "123456.1";
System.out.println(System.getProperty("user.dir"));
        roughClass obj= new roughClass();
        obj.getData();
	}
}
