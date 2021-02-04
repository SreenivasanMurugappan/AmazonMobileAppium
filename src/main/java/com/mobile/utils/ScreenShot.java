package com.mobile.utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;

public class ScreenShot {

	static boolean logWebElements = false;
	public static Properties miscProps = new Properties();
	private WebDriver driver;

	public static void getScreenshot(AppiumDriver driver, String outputlocation) throws IOException {
		System.out.println("Capturing the snapshot of the page ");
		String name = System.getProperty("user.dir") + "\\screenShots\\" + outputlocation + ".png";
		System.out.println(name);
		File srcFiler=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFiler, new File(name));
	}
}
