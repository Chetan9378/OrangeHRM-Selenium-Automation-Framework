package utils;

import java.util.UUID;

public class TestDataGenerator {

	public static String generateFirstName() 
	{

		return "Emp" + UUID.randomUUID().toString().substring(0, 5);
	}

	public static String generateLastName() 
	{

		return "Test" + UUID.randomUUID().toString().substring(0, 5);
	}
	
	public static String generateEmployeeId() 
	{
		int employeeId = 100000 + (int) (Math.random() * 900000);

		return String.valueOf(employeeId);
	}
}
