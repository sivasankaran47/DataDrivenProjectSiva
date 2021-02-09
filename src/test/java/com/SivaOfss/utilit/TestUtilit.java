package com.SivaOfss.utilit;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.SivaOfss.Base.TestBase;

public class TestUtilit extends TestBase {

	public static String screenShotPath;
	public static String screenShotName;

	public static void screenshot() throws IOException {

		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		screenShotName = "error.jpg";
		FileUtils.copyFile(srcfile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenShotName));
	}

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {
		String sheetName = m.getName();
		int TotRow = excel.getRowCount(sheetName);
		int TotCol = excel.getColumnCount(sheetName);
		// System.out.println(TotRow);

		Object[][] data = new Object[TotRow - 1][1];
		Hashtable<String, String> table = null;
		for (int rows = 2; rows <= TotRow; rows++) {
			table = new Hashtable<String, String>();
			for (int cols = 0; cols < TotCol; cols++) {
				// System.out.println(sheetName);
				// System.out.println(cols);
				// System.out.println(rows);
				table.put(excel.getCellData(sheetName, cols, 1), excel.getCellData(sheetName, cols, rows));
				data[rows - 2][0] = table;
				System.out.println(table);
				// System.out.println(excel.getCellData(sheetName, cols, rows));
			}
		}

		return data;
	}

}
