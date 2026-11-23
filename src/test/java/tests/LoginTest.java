package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest
{
	@Test
	public void verifySuccesssfulLogin()
	{
		LoginPage loginPage = new LoginPage(getDriver(), getWait());

		loginPage.login("Admin", "admin123");

		String actualDashboardHeading = getWait().until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//h6[normalize-space()='Dashboard']"))).getText();

		String expectedDashboardHeading = "Dashboard";

		Assert.assertEquals(actualDashboardHeading, expectedDashboardHeading);

		System.out.println("Login Assertion Passed");
	}
}
