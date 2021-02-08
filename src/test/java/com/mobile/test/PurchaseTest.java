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
 * Purchase Test
 */

public class PurchaseTest extends BaseTest {
	
	
 /**
  * purchase_Verification 
  */
  @Test
  public void purchase_Verification(){
    try {
      final PurchasePage purchase = new PurchasePage(driver, platform);
      purchase.addToCart(driver);
      LOGGER.info("Add products to cart");
      Reporter.log("Add products to cart");
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
}