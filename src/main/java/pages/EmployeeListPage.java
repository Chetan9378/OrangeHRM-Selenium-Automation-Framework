	package pages;
	
	import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WaitUtils;
	
	
	public class EmployeeListPage 
	{
	
		private WebDriver driver;
		private WebDriverWait wait;
		private WaitUtils waitUtils;
	
		public EmployeeListPage(WebDriver driver, WebDriverWait wait) 
		{
			this.driver = driver;
			this.wait = wait;
			this.waitUtils = new WaitUtils(wait);
			
		}
		
		private By employeeListTab = By.xpath("//a[normalize-space()='Employee List']");
		
		private By employeeIdField = By.xpath("//label[normalize-space()='Employee Id']/parent::div/following-sibling::div//input");
		
		private By searchButton = By.xpath("//button[normalize-space()='Search']");
		
		private By resultRow = By.cssSelector(".oxd-table-card");
		
		private By confirmDeleteButton = By.xpath("//button[normalize-space()='Yes, Delete']");
		
		private By successToast = By.cssSelector(".oxd-text--toast-message");
		
		private By noRecordsFound = By.xpath("//*[normalize-space()='No Records Found']");
		
		private By formLoader = By.cssSelector(".oxd-form-loader");
				
		public void clickEmployeeList() 
		{
		    waitUtils.waitForInvisibility(formLoader);
			
			WebElement employeeListElement = waitUtils.waitForPresence(employeeListTab);

			JavascriptExecutor js = (JavascriptExecutor) driver;

			js.executeScript("arguments[0].scrollIntoView({block:'center'});", employeeListElement);
			
			js.executeScript("arguments[0].click();", employeeListElement);
			
			wait.until(ExpectedConditions.urlContains("viewEmployeeList"));

		    waitUtils.waitForVisibility(employeeIdField);
			
	    }
	
		public void enterEmployeeId(String employeeId) 
		{
			waitUtils.waitForInvisibility(formLoader);
			
			waitUtils.waitForRefreshedClickable(employeeIdField).sendKeys(employeeId);
	    }
		
		public void clickSearch() 
		{
			waitUtils.waitForInvisibility(formLoader);
			waitUtils.waitForRefreshedClickable(searchButton).click();
	    }
		
		public boolean isEmployeeRecordDisplayed() 
		{
	        return waitUtils.waitForVisibility(resultRow).isDisplayed();
		}
		
		public void clickEditEmployee(String employeeId) 
		{
			
			waitUtils.waitForInvisibility(formLoader);
			
			By editButton = By.xpath("//div[contains(@class,'oxd-table-card')]" + "[.//*[normalize-space()='"
					+ employeeId + "']]" + "//button[.//i[contains(@class,'bi-pencil-fill')]]");
						
			wait.until(driver -> 
			{
				try {

					driver.findElement(editButton).click();

					return true;

				} catch (StaleElementReferenceException | ElementClickInterceptedException e) 
				{

					return false;
				}
		    });

			wait.until(ExpectedConditions.urlContains("viewPersonalDetails"));
				
		}
		
		public void clickDeleteEmployee(String employeeId)
		{
			By deleteButton = By.xpath("//div[contains(@class,'oxd-table-card')]" + "[.//*[normalize-space()='" + employeeId + "']]" +
					"//button[.//i[contains(@class,'bi-trash')]]");

			waitUtils.waitForRefreshedClickable((deleteButton)).click();

		}
		
		public void confirmDeleteEmployee() 
		{
			waitUtils.waitForClickable(confirmDeleteButton).click();
		}
		
		public String getDeleteSuccessMessage() 
		{
			return waitUtils.waitForVisibility(successToast).getText();
		}
		
		public boolean isNoRecordsFoundDisplayed() 
		{
			return waitUtils.waitForVisibility(noRecordsFound).isDisplayed();
		}
		
	}
