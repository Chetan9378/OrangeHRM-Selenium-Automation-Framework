package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

public class AddEmployeePage {
	private WebDriver driver;
	private WebDriverWait wait;
	private WaitUtils waitUtils;

	public AddEmployeePage(WebDriver driver, WebDriverWait wait) {

		this.driver = driver;
		this.wait = wait;
		this.waitUtils = new WaitUtils(wait);

	}

	private By addEmployeeButton = By
			.cssSelector("button[class='oxd-button oxd-button--medium oxd-button--secondary']");

	private By firstNameField = By.name("firstName");

	private By lastNameField = By.name("lastName");

	private By employeeIdField = By
			.xpath("//label[normalize-space()='Employee Id']" + "/parent::div/following-sibling::div//input");

	private By saveButton = By.xpath("//button[normalize-space()='Save']");

	private By successToast = By.cssSelector(".oxd-text--toast-message");

	private By formLoader = By.cssSelector(".oxd-form-loader");

	public void clickAddEmployee() {

		waitUtils.waitForClickable(addEmployeeButton).click();
	}

	public void enterFirstName(String firstName) {
		waitUtils.waitForVisibility(firstNameField).sendKeys(firstName);
	}

	public void enterLastName(String lastName) {
		waitUtils.waitForVisibility(lastNameField).sendKeys(lastName);
	}

	public String getEmployeeId() 
	{
		return waitUtils.waitForVisibility(employeeIdField).getAttribute("value");
	}

	public void enterEmployeeId(String employeeId) {
		
		waitUtils.waitForInvisibility(formLoader);

		WebElement employeeIdElement = waitUtils.waitForClickable(employeeIdField);

		employeeIdElement.click();

		employeeIdElement.sendKeys(Keys.CONTROL, "a");

		employeeIdElement.sendKeys(Keys.BACK_SPACE);

		employeeIdElement.sendKeys(employeeId);
	}

	public void clickSave() {

		waitUtils.waitForInvisibility(formLoader);
		waitUtils.waitForClickable(saveButton).click();
		waitUtils.waitForInvisibility(formLoader);
	}

	public String getSuccessMessage() {
		return waitUtils.waitForVisibility(successToast).getText();
	}

}
