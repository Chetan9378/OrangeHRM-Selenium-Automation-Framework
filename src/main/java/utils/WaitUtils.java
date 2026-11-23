package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

	private WebDriverWait wait;   		//stores the same explicit wait object that your BaseTest already creates.

	public WaitUtils(WebDriverWait wait) 
	{
		this.wait = wait;      			
	}

	public WebElement waitForVisibility(By locator) {

		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForClickable(By locator) {

		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitForInvisibility(By locator) {

		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public WebElement waitForRefreshedClickable(By locator) 
	{

		return wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
	}
	
	public WebElement waitForPresence(By locator) {

	    return wait.until(
	            ExpectedConditions.presenceOfElementLocated(locator)
	    );
	}
	
}