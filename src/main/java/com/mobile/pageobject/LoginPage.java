package com.mobile.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSFindBy;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;



import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;


public class LoginPage extends ObjectPage{
	
  public static final long  IMPLICITLY_WAIT_TIMEOUT = 10;
  
  private static final String A_AlreadySignInBtn = "com.amazon.mShop.android.shopping:id/sign_in_button";
  
  private static final String A_SignInBtn = "android.widget.Button";
  
  private static final String A_Email = "android.widget.EditText";
  
  private static final String A_Continue = "android.widget.Button";
  
  private static final String A_Password = "android.widget.EditText";
  
  // iOS locators
  //private static final String I_AlreadySignInBtn = "com.amazon.mShop.android.shopping:id/sign_in_button";
  
  //private static final String I_SignInBtn = "android.widget.Button";
  
  //private static final String I_Email = "android.widget.EditText";
  
  //private static final String I_Continue = "android.widget.Button";
  
  //private static final String I_Password = "android.widget.EditText";
  
  // iOS page factory
  //@iOSBy(id = I_AlreadySignInBtn)
  @AndroidBy(id = A_AlreadySignInBtn)
  public WebElement AlreadySignInBtn;
  
  // iOS page factory
  //@iOSBy(className = I_Email)
  @AndroidBy(className = A_Email)
  public WebElement Email;
  
  @AndroidBy(className = A_Continue)
  public WebElement Continue;
  
  @AndroidBy(className = A_Password)
  public WebElement Password;
  
  @AndroidBy(className = A_SignInBtn)
  public WebElement SignInBtn;
  
  public LoginPage(final WebDriver driver, final String platform) {
    super(driver, platform);
    // TODO Auto-generated constructor stub
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
  }

  // To Enter UserName and Password on Login
  public void loginAmazon(final WebDriver driver, final String username,
      final String password) throws InterruptedException {
    final WebDriverWait ww = new WebDriverWait(driver, 120);
    ww.until(ExpectedConditions.elementToBeClickable(By.id(A_AlreadySignInBtn)));
    AlreadySignInBtn.click();
    ww.until(ExpectedConditions.elementToBeClickable(By.className(A_Email)));
    Email.sendKeys(username);
    ww.until(ExpectedConditions.elementToBeClickable(By.className(A_Continue)));
    Continue.click();
    ww.until(ExpectedConditions.elementToBeClickable(By.className(A_Password)));
    Password.sendKeys(password);
    ww.until(ExpectedConditions.elementToBeClickable(By.className(A_SignInBtn)));
    SignInBtn.click();    
}
}
