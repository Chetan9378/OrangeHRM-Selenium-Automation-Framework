package base;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utils.ConfigReader;

public class BaseTest 
{
	
	private ThreadLocal<WebDriver> driver =
	        new ThreadLocal<>();

	private ThreadLocal<WebDriverWait> wait =
	        new ThreadLocal<>();
	
	private ThreadLocal<String> browserName =
	        new ThreadLocal<>();
	
    protected ConfigReader config;

	
	public String getBrowser() 
	{
	    return browserName.get();
	}

	public WebDriver getDriver() {
	    return driver.get();
	}

	public WebDriverWait getWait() {
	    return wait.get();
	}
	
	@BeforeMethod
	@Parameters("browser")
	public void setup(@Optional("chrome") String browser)
	{
	    browserName.set(browser);
		
		System.out.println("STARTING SETUP | Browser: " + browser);
		
		config = new ConfigReader();
		
		boolean isJenkins =  System.getenv("JENKINS_URL") != null;
		
		
		if (browser.equalsIgnoreCase("chrome")) 
		{

			ChromeOptions options = new ChromeOptions();

			options.addArguments("--guest");
			
			if (isJenkins) 
			{
				options.addArguments("--headless=new");
				options.addArguments("--window-size=1920,1080");
			}

			driver.set(new ChromeDriver(options));
			
		} 
		
		else if (browser.equalsIgnoreCase("edge")) 
		{
			
			EdgeOptions options = new EdgeOptions();
			
			if (isJenkins) 
			{
			    options.addArguments("--headless=new");
			    options.addArguments("--window-size=1920,1080");
			}
			
			EdgeDriverService service = new EdgeDriverService.Builder()
					.usingDriverExecutable(new File("C:\\WebDrivers\\Edge\\msedgedriver.exe")).build();
			
			driver.set(new EdgeDriver(service, options));
		} 
		
		else if (browser.equalsIgnoreCase("firefox")) 
		{
			FirefoxOptions options = new FirefoxOptions();
			

            if (isJenkins) 
            {
                options.addArguments("-headless");
            }

            driver.set(new FirefoxDriver(options));

			driver.set(new FirefoxDriver());
		} 
		
		else 
		{
		    throw new RuntimeException("Browser not supported: " + browser);
		}
		
		if (isJenkins) 
		{

			getDriver().manage().window().setSize(new Dimension(1920, 1080));

		} 
		
		else 
		{

			getDriver().manage().window().maximize();
		}
				
		String baseUrl = config.getProperty("baseUrl");
				
		getDriver().get(baseUrl);

		wait.set(new WebDriverWait(getDriver(), Duration.ofSeconds(30)));
				
	}
	
	@AfterMethod
	public void tearDown() 
	{
		WebDriver currentDriver = getDriver();


		if (currentDriver != null) 
		{
			currentDriver.quit();
			
			driver.remove();
	        wait.remove();
	        browserName.remove();
		}
	}
}
