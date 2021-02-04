package com.mobile.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mobile.common.BaseTest;

import io.appium.java_client.AppiumDriver;

/**
 * Abstract Web Page object. Will be inherited by all page objects.
 * 
 */
public abstract class ObjectPage extends BaseTest {

	private static final String NULL_DRIVER_MSG = "WebDriver is null ";

	public ObjectPage(final WebDriver driver, final String platform) {
		super();
		if (driver == null) {
			// LOG.error(NULL_DRIVER_MSG);
			throw new IllegalArgumentException(NULL_DRIVER_MSG);
		}
	}

	/**
	 * waitForElementToLoad - Waits for a specified element to be loaded
	 * @param driver - An Appium Driver
	 * @param anElement - Element to wait for
	 * @param timeInSecondsToWait - Amount of time to wait for the element to load
	 * @return  0 = Element loaded ok
	 *         -1 = Generic Error
	 *         -2 = Timeout while waiting
	 */
	public static void waitForElementToLoad( WebDriver driver , WebElement anElement , int timeInSecondsToWait ) throws TimeoutException
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
	 * @param miliSeconds
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
