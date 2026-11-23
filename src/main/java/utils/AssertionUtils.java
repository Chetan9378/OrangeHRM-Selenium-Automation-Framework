package utils;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class AssertionUtils 
{
	public static void verifyTrue(SoftAssert softAssert, boolean condition, String failureMessage, WebDriver driver,String screenshotName, String browser) 
	{

		if (!condition) 
		{

			ScreenshotUtils.captureScreenshot(driver, screenshotName + "_" + browser);
		}

		softAssert.assertTrue(condition, failureMessage);
	}
}