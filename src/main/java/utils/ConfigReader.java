package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private Properties properties;    //properties is a variable that will hold values from config file

	public ConfigReader() {

		properties = new Properties();    //creates the actual Properties object.

		try                               
		{

			FileInputStream file = new FileInputStream("src/test/resources/config.properties"); //Open the config.properties file so Java can read it.

			properties.load(file);            //loads all values from the file into memory

			file.close();                     //closes the file after reading it.

		} 
		
		catch (IOException e) 
		{

			e.printStackTrace();
		}
	}

	public String getProperty(String key)
	{

		return properties.getProperty(key);
	}
}