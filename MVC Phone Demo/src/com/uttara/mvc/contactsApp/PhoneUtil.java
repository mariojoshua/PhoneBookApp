package com.uttara.mvc.contactsApp;

/**
 * Utility class only for input validations
 *@author mariojoshuaaugustine
 */
import java.util.Date;

public class PhoneUtil {
	/*
	 * This method will validate the string given to check if it contains multiple
	 * words or special chars. If yes, it returns an error message else it returns constant
	 * string success!
	 */
	public static String validateName(String n) {
		if (n == null || n.trim().equals(""))
			throw new IllegalArgumentException("name cannot be null");

		String[] sa = n.split(" ");
		if (sa.length > 1)
			return "Name cannot contain multiple words! Enter a single worded string as input!";

		for (int i = 0; i < n.length(); i++) {
			char c = n.charAt(i);

			if (!(Character.isDigit(c) || Character.isLetter(c)))
				return "Name should not contain special chars!";
		}
		return Constants.SUCCESS;
	}
	
	
	public static String validateDate(String date) {
		//Check if input date is before current date
		//To validate current date and time
//		Date d = new Date;
//		d.validateTime();
//
//		//To convert String to date
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		Date dt = sdf.parse(String date);
//
//		//to convert data to String
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
//		String plDt = sdf.format(d);
		return Constants.SUCCESS;
		
	}
	
	public static String validateEmail(String email) {
		return Constants.SUCCESS;
	}
	
	public static String validatePhoneNumber(String num) {
		//Use Regex  or starts with to check if it begins with right numbers
		return Constants.SUCCESS;
	}
	
	public static String validateTags(String tag) {
		return Constants.SUCCESS;
	}
	
	//	Address Validations
	public static String homeNumber;
	public static String streetAddress;
	public static String pincode;
	public static String city;
	public static String state;
	public static String country;
	
	static String validateHomeNumber(String homeNumber) {
		return Constants.SUCCESS;
	}


	static String validateStreetAddress(String streetAddress) {
		return Constants.SUCCESS;
	}


	static String validatePincode(String pincode) {
		return Constants.SUCCESS;
	}


	static String validateCity(String city) {
		return Constants.SUCCESS;
	}


	static String validateState(String state) {
		return Constants.SUCCESS;
	}


	static String validateCountry(String country) {
		return Constants.SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
