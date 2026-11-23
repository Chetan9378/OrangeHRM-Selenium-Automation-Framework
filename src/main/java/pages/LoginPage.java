package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

import base.BaseTest;

public class LoginPage extends BaseTest
{
	private WebDriver driver;  //This stores the browser reference inside LoginPage.
							   //We use private because this driver belongs to this page class

	private WaitUtils waitUtils;
	
	public LoginPage(WebDriver driver, WebDriverWait wait) 
	{

		this.driver = driver;
		this.waitUtils = new WaitUtils(wait);
	}

	private By usernameField = By.name("username");

	private By passwordField = By.name("password");

	private By loginButton = By.cssSelector("button[type='submit']");


	public void enterUsername(String username) 
	{
		waitUtils.waitForVisibility(usernameField).sendKeys(username);
		//driver.findElement(usernameField).sendKeys(username);
	}


	public void enterPassword(String password) {

		waitUtils.waitForVisibility(passwordField).sendKeys(password);
	}


	public void clickLogin() {

		waitUtils.waitForClickable(loginButton).click();
	}


	public void login(String username, String password) {

		enterUsername(username);

		enterPassword(password);

		clickLogin();
	}
}
