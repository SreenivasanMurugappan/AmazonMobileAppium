package com.mobile.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mobile.common.BaseTest;
import com.mobile.pageobject.LoginPage;
import com.mobile.pageobject.PurchasePage;
import com.mobile.utils.ScreenShot;

/**
 * @description Add product in cart
 * @author Sreenivasan M
 * @creationDate 31-01-2021
 * @modificationDdate 31-01-2021
 */

public class PurchaseTest extends BaseTest {

  @Test
  @Parameters({ "appName", "platformName", "platformVersion", "deviceName"})
  public void purchase_Verification(final String appName, final String platformName, final String platformVersion, 
		  final String deviceName){
    try {
      final PurchasePage purchase = new PurchasePage(driver, platform);
      
      // Add to cart
      purchase.addToCart(driver);
      LOGGER.info("Add products to cart");
      Reporter.log("Add products to cart");
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