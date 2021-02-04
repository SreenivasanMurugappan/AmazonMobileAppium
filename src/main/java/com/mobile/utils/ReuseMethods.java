package com.mobile.utils;

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

public class ReuseMethods extends BaseTest {
	
	// Scroll to Xpath
	public static void scrolltoXPath(WebDriver driver, String xPath) {

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

	// Selecting an element using tap function
	public void tapElement(final AppiumDriver driver, String xpathIdentifier) throws InterruptedException {
		final WebElement ele = driver.findElement(By.xpath(xpathIdentifier));
		System.out.println("Lable name of the element : " + ele.getText());
		Point loc = ele.getLocation();
		int x = loc.getX();
		int y = loc.getY();
		AndroidTouchAction touch = new AndroidTouchAction(driver);
		touch.tap(ElementOption.element(ele, x, y)).perform();
		//Press
		Thread.sleep(1000);
	}

	// Search Context
	public void findContexts(final AppiumDriver driver) {
		Set<String> availableContexts = driver.getContextHandles();
		System.out.println("Total No of Context Found After we reach to WebView = "+ availableContexts.size());
		for(String context : availableContexts) {
			System.out.println(context);
			if(context.contains("Web View")){
				System.out.println("Context Name is " + context);
				// 4.3 Call context() method with the id of the context you want to access and change it to WEBVIEW_1
				//(This puts Appium session into a mode where all commands are interpreted as being intended for automating the web view)
				driver.context(context);
				break;
			}
		}
	}
	
	
}
