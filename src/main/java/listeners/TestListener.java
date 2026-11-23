package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import utils.ExtentManager;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener 
{

	private ExtentReports extent = ExtentManager.getInstance();

	private ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	private String getBrowser(ITestResult result)
	{
		BaseTest baseTest = (BaseTest) result.getInstance();

		return baseTest.getBrowser();
	}
	
	@Override
	public void onTestStart(ITestResult result) {

		String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");

		ExtentTest extentTest = extent.createTest(result.getName() + " - " + browser);

		test.set(extentTest);

		test.get().info("Test execution started on browser: " + browser);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");

		test.get().pass("Test Passed on browser: " + browser);

		test.remove();
	}

	@Override
	public void onTestFailure(ITestResult result) {

		String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");

		test.get().fail("Test Failed on browser: " + browser);

		test.get().fail(result.getThrowable());

		Object testObject = result.getInstance();

		BaseTest baseTest = (BaseTest) testObject;

		WebDriver driver = baseTest.getDriver();

		if (driver != null) {

			String screenshotName = result.getName() + "_" + browser;

			String screenshotPath = ScreenshotUtils.captureScreenshot(driver, screenshotName);

			try {

				test.get().addScreenCaptureFromPath(screenshotPath);

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		test.remove();
	}

	@Override
	public void onFinish(ITestContext context) {

		extent.flush();
	}
}