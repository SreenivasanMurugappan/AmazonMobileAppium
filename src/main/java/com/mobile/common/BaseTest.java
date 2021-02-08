package com.mobile.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

/**
 * BaseTest for driver initialization
 */
public class BaseTest {
	public final static Logger LOGGER =  
			Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	protected AppiumDriver driver;
	protected String           platform;
	public final static String    ANDROID_PLATFORM  = "Android";
	public final static String    IOS_PLATFORM      = "IOS";
	
	
	public static ResourceBundle propReader = ResourceBundle.getBundle("environmentInfo.TestConfig");
	private static final String appPackage   = propReader.getString("appPackage");
	private static final String appActivity   = propReader.getString("appActivity");
	
	/**
	 * setUp - Driver initization
	 * @param appName - Application Name
	 * @param platformName - Android or iOS
	 * @param platformVersion - Version number
	 * @param deviceName - Emulator or Simulator name
	 */
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
				capabilities.setCapability("appPackage", appPackage);
				capabilities.setCapability("appActivity",appActivity);
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

	/**
	 * getSeleniumUrl - Appium Url
	 * @return Url - SeleniumUrl
	 */
	public static URL getSeleniumUrl() throws MalformedURLException {
		String url = null;
		url = "http://0.0.0.0:4723/wd/hub";
		System.out.println("URL: " + url);
		return new URL(url);
	}

	/**
	 * buildAppPath - Applicaton path apk or app file location
	 * @param appName - Application Name
	 * @param platformName - Android or iOS
	 */
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

	/**
	 * throwMissingPlatformException - Throw exception if platform is missing
	 */
	private static void throwMissingPlatformException() {
		throw new RuntimeException(
				"Platform parameter must be passed, and must equal " + ANDROID_PLATFORM
				+ " or " + IOS_PLATFORM + " and is case sensitive.");
	}
	
	/**
	 * tearDown - closing the driver
	 */
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		quitDriver(driver);
	}

	/**
	 * quitDriver - Driver quit
	 */
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
