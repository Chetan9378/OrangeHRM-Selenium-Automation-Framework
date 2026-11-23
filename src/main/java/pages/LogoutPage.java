package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

public class LogoutPage 
{
	private WebDriver driver;
	private WebDriverWait wait;
	private WaitUtils waitUtils;

	public LogoutPage(WebDriver driver, WebDriverWait wait) {

		this.driver = driver;
		this.wait = wait;	
		this.waitUtils = new WaitUtils(wait);
	}
	
	private By userDropdown = By.xpath("//p[@class='oxd-userdropdown-name']");
	
	private By logoutButton = By.xpath("//a[normalize-space()='Logout']");
	
	private By userNameField = By.name("username");
	
	public void clickUserDropdown()
	{
		waitUtils.waitForClickable(userDropdown).click();
	}
	
	public void clickLogout()
	{
		waitUtils.waitForClickable(logoutButton).click();
	}
	
	public boolean isLoginPageDisplayed() 
	{
		return waitUtils.waitForVisibility(userNameField).isDisplayed();
	}

}
