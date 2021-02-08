package com.mobile.test;

import java.io.IOException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mobile.common.BaseTest;
import com.mobile.pageobject.LoginPage;
import com.mobile.utils.ReuseMethods;
import com.mobile.utils.ScreenShot;

/**
 * Login Test
 */

public class LoginTest extends BaseTest {
	
	String path = System.getProperty("user.dir") + "\\src\\test\\resources\\environmentInfo\\TestData.xlsx";
	String sheetName = "creds";
	
  /**
   * login_Verification 
   * @param username from excel sheet
   * @param password from excel sheet
   */
  @Test(dataProvider = "userDetails")
  public void login_Verification(String username, String password){
    try {
      final LoginPage login = new LoginPage(driver, platform);
      login.loginAmazon(driver, username, password);
      LOGGER.info("Logging into Amazon app with " + username + "account <br>");
      Reporter.log("Logging into Amazon app with " + username + "account <br>");
      ScreenShot.getScreenshot(driver, "SUCCESS_" + this.getClass().getName());

    } catch (final Exception e) {      
      LOGGER.info("Test failed");
		try {
			ScreenShot.getScreenshot(driver, "FAILURE_" + this.getClass().getName());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		e.printStackTrace();
		Assert.fail("Test case fail due to " + e.getMessage());
    }
  }
  
  @DataProvider(name = "userDetails")
  public Object[][] endUserData() throws Exception
  {
	  ReuseMethods reuse = new ReuseMethods();
      return reuse.testData(path, sheetName);
  }
}