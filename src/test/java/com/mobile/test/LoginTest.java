package com.mobile.test;

import java.io.IOException;
import java.util.ResourceBundle;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mobile.common.BaseTest;
import com.mobile.pageobject.LoginPage;
import com.mobile.utils.ScreenShot;

/**
 * @description Login to Amazon
 * @author Sreenivasan M
 * @creationDate 31-01-2021
 * @modificationDdate 31-01-2021
 */

public class LoginTest extends BaseTest {
	
  public static ResourceBundle creds = ResourceBundle.getBundle("environmentInfo.UserCredentials");

  private static final String username   = creds.getString("username");
  private static final String password   = creds.getString("password");

  @Test
  @Parameters({ "appName", "platformName", "platformVersion", "deviceName"})
  public void login_Verification(final String appName, final String platformName, final String platformVersion, 
		  final String deviceName){
    try {
      final LoginPage login = new LoginPage(driver, platform);
      
      // Login into Amazon app
      login.loginAmazon(driver, username, password);
      LOGGER.info("Logging into Amazon app with " + username + "account <br>");
      Reporter.log("Logging into Amazon app with " + username + "account <br>");
      ScreenShot.getScreenshot(driver, "SUCCESS_" + this.getClass().getName());

    } catch (final Exception e) {      
      LOGGER.info("Test failed");
		try {
			ScreenShot.getScreenshot(driver, "FAILURE_" + this.getClass().getName());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		e.printStackTrace();
		Assert.fail("Test case fail due to " + e.getMessage());
    }
  }
}