package com.mobile.pageobject;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.mobile.common.BaseTest;

import io.appium.java_client.AppiumDriver;

/**
 * Abstract object page will be inherited by all page objects.
 * 
 */
public abstract class CommonPage extends BaseTest {

	private static final String NULL_DRIVER_MSG = "AppiumDriver is null ";

	public CommonPage(final AppiumDriver driver, final String platform) {
		super();
		if (driver == null) {
			throw new IllegalArgumentException(NULL_DRIVER_MSG);
		}
	}

	/**
	 * waitForElementToLoad - Waits for a specified element to be loaded
	 * @param driver - Appium Driver
	 * @param anElement - Element to wait for
	 * @param timeInSecondsToWait - Amount of time to wait for the element to load
	 */
	public static void waitForElementToLoad( AppiumDriver driver , WebElement anElement , int timeInSecondsToWait ) throws TimeoutException
	{
		boolean staleFlag = false;
		do
		{
			if( staleFlag )
				staleFlag = false;
			try
			{
				WebDriverWait wait = new WebDriverWait( driver , timeInSecondsToWait );
				wait.until( ExpectedConditions.elementToBeClickable( anElement ) );
			} 
			catch ( StaleElementReferenceException e )
			{
				LOGGER.info( "Detected stale elements" );
				staleFlag = true;
				SetDelay( 1000 );
			}
		} while( staleFlag );
	}

	/**
	 * SetDelay
	 * @param miliSeconds - miliSeconds
	 */
	public static void SetDelay( int miliSeconds )
	{
		try 
		{
			Thread.sleep( miliSeconds );
		} 
		catch ( InterruptedException e ) 
		{
			System.out.println( "An error happened while in sleep" );
		}
	}
}
