package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

public class DashboardPage 
{
	private WebDriver driver;
	private WebDriverWait wait;
	private WaitUtils waitUtils;
	
	private By formLoader = By.cssSelector(".oxd-form-loader");
	
	public DashboardPage(WebDriver driver, WebDriverWait wait) {

		this.driver = driver;
		this.wait = wait;
		this.waitUtils = new WaitUtils(wait);
		
	}
	
	private By pimMenu = By.xpath("//span[normalize-space()='PIM']");
	
	public void clickPIM()
	{
	    waitUtils.waitForInvisibility(formLoader);
	    
		wait.until(driver -> 
		{
			try 
			{

				WebElement pimElement = driver.findElement(pimMenu);

				pimElement.click();

				return true;

			} catch (StaleElementReferenceException | ElementClickInterceptedException e)
			{

				return false;
			}
		});

		wait.until(ExpectedConditions.urlContains("pim"));
	}
	
}
