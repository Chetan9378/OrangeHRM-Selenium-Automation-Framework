package tests;

import org.testng.annotations.Test;
import utils.TestDataGenerator;
import org.testng.asserts.SoftAssert;
import base.BaseTest;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.EditEmployeePage;
import pages.EmployeeListPage;
import pages.LoginPage;
import pages.LogoutPage;

import org.testng.annotations.Listeners;
import listeners.TestListener;
import utils.AssertionUtils;

@Listeners(TestListener.class)
public class AddEmployeeTest extends BaseTest
{
	@Test
	public void verifyAddEmployee()
	{
		SoftAssert softAssert = new SoftAssert();
		
		String browser = getBrowser();
		
		LoginPage loginPage = new LoginPage(getDriver(), getWait());
		loginPage.login(config.getProperty("username"),config.getProperty("password"));

		DashboardPage dashboardPage = new DashboardPage(getDriver(), getWait());
		dashboardPage.clickPIM();

		AddEmployeePage addEmployeePage = new AddEmployeePage(getDriver(), getWait());
		addEmployeePage.clickAddEmployee();
		
		String firstName = TestDataGenerator.generateFirstName();
		String lastName = TestDataGenerator.generateLastName();
		
		addEmployeePage.enterFirstName(firstName);
		addEmployeePage.enterLastName(lastName);

		String employeeId = TestDataGenerator.generateEmployeeId();
		
		addEmployeePage.enterEmployeeId(employeeId);
		
		String actualEmployeeId = addEmployeePage.getEmployeeId();

		addEmployeePage.clickSave();

		String successMessage = addEmployeePage.getSuccessMessage();
		
		AssertionUtils.verifyTrue(softAssert, successMessage.contains("Successfully"), "Employee creation failed",
				getDriver(), "AddEmployeeFailure",browser);
		
		System.out.println(browser + " | Employee added successfully");
		
		EmployeeListPage employeeListPage = new EmployeeListPage(getDriver(), getWait());
				
		employeeListPage.clickEmployeeList();
				
		employeeListPage.enterEmployeeId(employeeId);
		
		employeeListPage.clickSearch();
		
		boolean employeeFound = employeeListPage.isEmployeeRecordDisplayed();
		
		AssertionUtils.verifyTrue(softAssert, employeeFound, "Employee record was not found", getDriver(),
				"SearchEmployeeFailure",browser);

		System.out.println(browser + " | Employee search assertion passed for ID is: " +employeeId);
		
		employeeListPage.clickEditEmployee(employeeId);
		
		EditEmployeePage editEmployeePage = new EditEmployeePage(getDriver(), getWait());
		
		String updatedFirstName = TestDataGenerator.generateFirstName();

		String updatedLastName = TestDataGenerator.generateLastName();
		
		editEmployeePage.updateFirstname(updatedFirstName);
		
		editEmployeePage.updateLastname(updatedLastName);
		
		editEmployeePage.clickSave();
		
		String updateMessage = editEmployeePage.getSuccessMessage();
		
		AssertionUtils.verifyTrue(softAssert, updateMessage.contains("Successfully"), "Employee Update failed", getDriver(),
				"EditEmployeeFailure",browser);
				
		System.out.println(browser + " | Employee updated successfully");
		
		employeeListPage.clickEmployeeList();
		
		employeeListPage.enterEmployeeId(employeeId);
	
		employeeListPage.clickSearch();
		
		employeeListPage.clickDeleteEmployee(employeeId);
		
		employeeListPage.confirmDeleteEmployee();
		
		String deleteMessage = employeeListPage.getDeleteSuccessMessage();
		
		AssertionUtils.verifyTrue(softAssert, deleteMessage.contains("Successfully"), "Employee deletion failed",
				getDriver(), "DeleteEmployeeFailure",browser);
		
		System.out.println(browser + " | Employee deleted successfully");
		
		employeeListPage.clickSearch();
		
		boolean employeeDeleted = employeeListPage.isNoRecordsFoundDisplayed();

		AssertionUtils.verifyTrue(softAssert, employeeDeleted, "Deleted employee is still appearing in search results",
				getDriver(), "DeleteVerificationFailure", browser);
		
		System.out.println(browser + " | Deletion verification passed. Employee ID " + employeeId + " is no longer available.");
		
		LogoutPage logoutPage = new LogoutPage(getDriver(), getWait());

		logoutPage.clickUserDropdown();

		logoutPage.clickLogout();

		System.out.println(browser + " | Logout completed successfully");
		
		boolean loginPageDisplayed = logoutPage.isLoginPageDisplayed();
		
		AssertionUtils.verifyTrue(softAssert, loginPageDisplayed, "Logout failed - Login page was not displayed",
				getDriver(), "LogoutFailure", browser);
								
		softAssert.assertAll();
			
	}
}