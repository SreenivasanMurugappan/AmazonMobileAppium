package com.mobile.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

/*
 * Author Sreenivas
 */
public class BaseTest {
	public final static Logger LOGGER =  
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	protected AppiumDriver driver;
	protected String           platform;
	public final static String    ANDROID_PLATFORM  = "Android";
	public final static String    IOS_PLATFORM      = "IOS";

	@BeforeMethod
	@Parameters({ "appName", "platformName", "platformVersion", "deviceName"})
	public void setUp(final String appName, final String platformName, final String platformVersion, 
			final String deviceName)
					throws Exception {
		final String appPath = buildAppPath(appName, platformName);
		final DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("platformName", platformName);
		capabilities.setCapability("platformVersion", platformVersion);
		capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability("appLocation", appPath);
		capabilities.setCapability("noReset", false);
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities.setCapability("resetKeyboard", true);
		if (platformName == null) {
			throwMissingPlatformException();
		} else if (platformName.equals(ANDROID_PLATFORM)) {
			if (appName.contentEquals("amazon")) {
				capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping");
				capabilities.setCapability("appActivity","com.amazon.mShop.home.HomeActivity");
			} else  {
				capabilities.setCapability("browserName", "Chrome");
				capabilities.setCapability("setWebContentsDebuggingEnabled", true);
			}
			driver = new AndroidDriver<>(getSeleniumUrl(), capabilities);
		} else if (platformName.equals(IOS_PLATFORM)) {
			if (appName.contentEquals("amazon")) {
				capabilities.setCapability("bundleId"," ");
			} else {
				capabilities.setCapability("browserName", "Safari");
			}
			capabilities.setCapability("launchTimeout", 60000);
			capabilities.setCapability("showIOSLog", "true");
			capabilities.setCapability("newCommandTimeout", 600);
			capabilities.setCapability("waitForAppScript", "$.delay(10000); true;");
			driver = new IOSDriver(getSeleniumUrl(), capabilities);
		} 
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
	}

	public static URL getSeleniumUrl() throws MalformedURLException {
		String url = null;
		url = "http://0.0.0.0:4723/wd/hub";
		System.out.println("URL: " + url);
		return new URL(url);
	}

	protected static String buildAppPath(final String appName,
			final String platformName) {
		String appPath = null;
		String fileLocation = null;
		String path = System.getProperty("user.dir") + "\\app\\";
		if (platformName.equals("Android")) {
			fileLocation = "Amazon_shopping.apk";
		} else {
			fileLocation = "Amazon_shopping.app";
		}
		appPath = path + fileLocation;
		return appPath;
	}

	private static void throwMissingPlatformException() {
		throw new RuntimeException(
				"Platform parameter must be passed, and must equal " + ANDROID_PLATFORM
				+ " or " + IOS_PLATFORM + " and is case sensitive.");
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		quitDriver(driver);
	}

	protected static void quitDriver(AppiumDriver driver) {
		try {
			if (driver != null) {
				driver.quit();
			}
		} catch (Exception e) {
			LOGGER.warning("Teardown failed with the following error: "+e);;
		}    
	}
}
