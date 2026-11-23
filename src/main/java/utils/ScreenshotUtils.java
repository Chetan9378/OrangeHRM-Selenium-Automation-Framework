package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

	public static String captureScreenshot(WebDriver driver, String testName) {

		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String screenshotPath = "screenshots/" + testName + ".png";

		File destination = new File(screenshotPath);

		try 
		{

			Files.createDirectories(destination.getParentFile().toPath());

			Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

		} 
		catch (IOException e) 
		{

			e.printStackTrace();
		}

		return screenshotPath;
	}
}