package com.mobile.utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;

/**
 * Screenshot for success and failures
 * 
 */
public class ScreenShot {

	static boolean logWebElements = false;
	public static Properties miscProps = new Properties();
	/**
	 * getScreenshot - Screenshot of the application
	 * @param driver - Appium Driver
	 * @param outputlocation - Output of the file
	 */
	public static void getScreenshot(final AppiumDriver driver, String outputlocation) throws IOException {
		System.out.println("Capturing the snapshot of the page ");
		String name = System.getProperty("user.dir") + "\\screenShots\\" + outputlocation + ".png";
		System.out.println(name);
		File srcFiler=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFiler, new File(name));
	}
}
