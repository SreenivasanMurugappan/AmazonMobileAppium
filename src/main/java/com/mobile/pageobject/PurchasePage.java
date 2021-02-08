package com.mobile.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.mobile.utils.ReuseMethods;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * Purchase page variables, elements and methods page
 */
public class PurchasePage extends CommonPage {
	
  public static final long  IMPLICITLY_WAIT_TIMEOUT = 10;
  
  private static final String A_SkipSigninBtn = "com.amazon.mShop.android.shopping:id/skip_sign_in_button";
  
  private static final String A_SearchText = "com.amazon.mShop.android.shopping:id/rs_search_src_text";
  
  private static final String A_Continue = "android.widget.Button";
  
  private static final String A_Password = "android.widget.EditText";
  
  private static final String A_ScrollToObject = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ViewAnimator/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[5]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout";
  
  @AndroidBy(id ="com.amazon.mShop.android.shopping:id/skip_sign_in_button")
  public static WebElement SkipSignIn;
  
  @AndroidBy(id = A_SkipSigninBtn)
  public WebElement SkipSigninBtn;
  
  @AndroidBy(id = A_SearchText)
  public WebElement SearchText;
  
  @AndroidBy(className = A_Continue)
  public WebElement Continue;
  
  @AndroidBy(className = A_Password)
  public WebElement Password;
  
  @AndroidBy(xpath = A_ScrollToObject)
  public WebElement ScrollToObject;
  
  /**
   * PurchasePage constructor
   * @param driver - Appium Driver
   * @param platform - Android or iOS
   */  
  public PurchasePage(final AppiumDriver driver, final String platform) {
    super(driver, platform);
    // TODO Auto-generated constructor stub
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
  }

  /**
   * addToCart - this function is to add the product in the cart
   * @param driver - Appium Driver
   */
  public void addToCart(final AppiumDriver driver) throws InterruptedException {
    final WebDriverWait ww = new WebDriverWait(driver, 120);
    ww.until(ExpectedConditions.elementToBeClickable(By.id(A_SkipSigninBtn)));
    SkipSigninBtn.click();
    waitForElementToLoad(driver, SearchText, 60);
    SearchText.sendKeys("65 inch tv " + Keys.ENTER);
    ReuseMethods reuse = new ReuseMethods();
    reuse.scrolltoXPath(driver, A_ScrollToObject);
    reuse.tapElement(driver, A_ScrollToObject);
    reuse.findContexts(driver);
    }
}
