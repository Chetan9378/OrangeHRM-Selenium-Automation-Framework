package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

public class EditEmployeePage 
{
	private WebDriver driver;
	private WebDriverWait wait;
	private WaitUtils waitUtils;

	public EditEmployeePage(WebDriver driver, WebDriverWait wait) {

		this.driver = driver;
		this.wait = wait;
		this.waitUtils = new WaitUtils(wait);

	}

	private By firstNameField = By.name("firstName");

	private By lastNameField = By.name("lastName");

	private By saveButton = By.xpath("//button[normalize-space()='Save']");

	private By successToast = By.cssSelector(".oxd-text--toast-message");
	
	private By formLoader = By.cssSelector(".oxd-form-loader");
	
	public void updateFirstname(String newFirstName) 
	{
		waitUtils.waitForVisibility(firstNameField).clear();
		waitUtils.waitForVisibility(firstNameField).sendKeys(newFirstName);
	}
	
	public void updateLastname(String newLastName) 
	{
		waitUtils.waitForVisibility(lastNameField).clear();
		waitUtils.waitForVisibility(lastNameField).sendKeys(newLastName);
	}
	
	public void clickSave() 
	{
		waitUtils.waitForInvisibility(formLoader);
		
		wait.until(driver -> 
		{

			try {

				WebElement saveElement = driver.findElement(saveButton);

				JavascriptExecutor js = (JavascriptExecutor) driver;

				js.executeScript("arguments[0].scrollIntoView({block:'center'});", saveElement);

				waitUtils.waitForInvisibility(formLoader);

				WebElement freshSaveButton = waitUtils.waitForRefreshedClickable(saveButton);

				freshSaveButton.click();

				return true;
				
				} 
			
			catch (StaleElementReferenceException | ElementClickInterceptedException e) 
			{

				return false;
			}
		});
		 
		waitUtils.waitForInvisibility(formLoader);  

    }
	
	public String getSuccessMessage() 
	{
		return waitUtils.waitForVisibility(successToast).getText();
	}
	
}
