package com.mobile.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mobile.common.BaseTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.ElementOption;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * ReuseMethods to define frequently used actions in an application.
 * 
 */
public class ReuseMethods extends BaseTest {
	
	/**
	 * scrolltoXPath - Scroll to element function
	 * @param driver - Appium Driver
	 * @param xPath - xpath of locators/elements
	 */
	public static void scrolltoXPath(final AppiumDriver driver, String xPath) {

		do {
			try {
				driver.findElement(By.xpath(xPath));
				break;

			} catch (Exception NoSuchElementException) {

				Map<String, Object> params = new HashMap<>();
				params.put("end", "40%,90%");
				params.put("start", "40%,20%");
				params.put("duration", "2");
				Object res = ((RemoteWebDriver) driver).executeScript("mobile:touch:swipe", params);
			}
		} while (true);
	}

	/**
	 * tapElement - Tap element using TouchAction
	 * @param driver - Appium Driver
	 * @param xpathIdentifier - xpath of locators/elements
	 */
	public void tapElement(final AppiumDriver driver, String xpathIdentifier) throws InterruptedException {
		final WebElement ele = driver.findElement(By.xpath(xpathIdentifier));
		System.out.println("Lable name of the element : " + ele.getText());
		Point loc = ele.getLocation();
		int x = loc.getX();
		int y = loc.getY();
		AndroidTouchAction touch = new AndroidTouchAction(driver);
		touch.tap(ElementOption.element(ele, x, y)).perform();
	}

	/**
	 * findContexts - Find webviews
	 * @param driver - Appium Driver
	 */
	public void findContexts(final AppiumDriver driver) {
		Set<String> availableContexts = driver.getContextHandles();
		System.out.println("Total No of Context Found After we reach to WebView = "+ availableContexts.size());
		for(String context : availableContexts) {
			System.out.println(context);
			if(context.contains("Web View")){
				System.out.println("Context Name is " + context);
				driver.context(context);
				break;
			}
		}
	}
	
	/**
	 * testData - From excel sheet reader
	 * @param excelFileLocation - File location
	 * @param sheetName - Excel sheet name
	 */
	public static Object[][] testData(String excelFileLocation, String sheetName) throws Exception
	    {
		 FileInputStream file= new FileInputStream(excelFileLocation);
         XSSFWorkbook workbook = new XSSFWorkbook(file);
         XSSFSheet sheet = workbook.getSheet(sheetName);
         int rowCount = sheet.getLastRowNum();
         int columnCount = sheet.getRow(0).getLastCellNum();
         Object[][] data = new Object[rowCount][columnCount];
         for (int i = 1; i <= rowCount; i++) {
             XSSFRow row = sheet.getRow(i);
             for (int j = 0; j < columnCount; j++) {
                 XSSFCell cell = row.getCell(j);
                 DataFormatter formatter = new DataFormatter();
                 String val = formatter.formatCellValue(cell);
                 data[i - 1][j] = val;
             }
         }
         return data;
     }
}