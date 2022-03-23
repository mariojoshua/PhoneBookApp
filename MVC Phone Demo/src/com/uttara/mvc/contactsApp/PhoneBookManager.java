package com.uttara.mvc.contactsApp;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * This class will hold the view and the controller in flavor 1 of MVC(Desktop
 * App) design pattern
 * 
 * @author mariojoshuaaugustine
 * @version 1.0
 * @since 2021-12-01
 * 
 */

public class PhoneBookManager {

	// scanner1 for numbers, scanner2 for Strings
	private static Scanner numberScanner = null;
	private static Scanner stringScanner = null;
	private static PhoneBookService phoneBookService = null;
	private static String phoneBookName = null, contactName = null, phoneNumber1 = null;
	private static String phoneNumber2;
	private static String dateOfBirth;

	/**
	 * These methods forms the View of the application these method will display the
	 * menu, accept the inputs, display success/error messages to the user and
	 * invoke the methods of model!
	 */

	public static void main(String[] args) {

		try {
			numberScanner = new Scanner(System.in);
			stringScanner = new Scanner(System.in);
			int choice1 = 0;

			phoneBookService = new PhoneBookService();
			while (choice1 != 6) {
				System.out.println("\n\tContacts Book Menu");
				System.out.println("*********************************\n");
				System.out.println("Press [1] to Create Contacts Book");
				System.out.println("Press [2] to Load Contact Books");
				System.out.println("Press [3] to Search contacts");
				System.out.println("Press [4] to List contacts");
				System.out.println("Press [5] for Birthday reminders");
				System.out.println("Press [6] to Exit");
				System.out.println("\n*********************************");
				System.out.println("Enter choice\f");

				/*
				 * until user gives only an int value, keep showing error message and ignore
				 * token read
				 */
				while (!numberScanner.hasNextInt()) {
					System.out.println("Kindly give numbers between 1 to 6 only\n\f");
					numberScanner.next();
				}

				// read the valid token integer input from scanner
				choice1 = numberScanner.nextInt();
				Logger.getInstance().log("choice = " + choice1);
				// System.out.println("choice = " + ch1);

				switch (choice1) {
				case 1:
					/*
					 * Creating a Contacts Book
					 */
					System.out.println("Creating phone book...");
					System.out.println("Enter name of phone book");
					phoneBookName = stringScanner.nextLine();
					// input validations!
					String result = PhoneUtil.validateName(phoneBookName);
					// until the input validations succeed, keep asking the user to give new input
					// and show error msg

					while (!result.equals(Constants.SUCCESS)) {
						System.out
								.println("Enter proper name which single word, no spl char and starts with letter...");
						phoneBookName = stringScanner.nextLine();
						result = PhoneUtil.validateName(phoneBookName);
					}

					if (phoneBookService.phoneBookExists(phoneBookName)) {
						System.out.println("Name already exists, Opening Phone Book");
					} else {
						System.out.println("Creating new phone book, Opening Phone Book " + phoneBookName);
					}
					Logger.getInstance().log("phoneBookName = " + phoneBookName);

					showsContactsMenu();
					break;
				case 2:
					System.out.println("Enter name of existing contacts book");
					if (phoneBookService.phoneBookExists(phoneBookName)) {
						System.out.println("loading phone book.. " + phoneBookName);
					} else {
						System.out.println("Phone Book with name " + phoneBookName + " does not exist.");
					}

					break;
				case 3:
					System.out.println("searching phone book...");
					break;
				case 4:

					System.out.println("listing phone book...");
					System.out.println(phoneBookService.listContacts(phoneBookName));
					break;
				case 5:
					System.out.println("listing birthday reminders...");
					break;
				case 6:
					System.out.println("Exiting...Have a great day");
					break;
				default:
					System.out.println("Kindly enter choice between 1 to 6\n");
					break;

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (numberScanner != null) {
					numberScanner.close();
					Logger.getInstance().log("scanner1 closed " + numberScanner);
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
			try {
				if (stringScanner != null) {
					stringScanner.close();
					Logger.getInstance().log("scanner2 closed " + stringScanner);
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}

	}

	public static void showsContactsMenu() {
		int choice2 = 0;
		while (choice2 != 6) {
			System.out.println("\f\tContacts Menu");
			System.out.println("-------------------------------\n");
			System.out.println("Press 1 to Add contact");
			System.out.println("Press 2 to Edit a contact");
			System.out.println("Press 3 to Remove a contact");
			System.out.println("Press 4 to List contacts");
			System.out.println("Press 5 to Search contact");
			System.out.println("Press 6 to go back");
			System.out.println("\n-------------------------------");
			while (!numberScanner.hasNextInt()) {
				System.out.println("Enter only numbers");
				numberScanner.next();
			}
			choice2 = numberScanner.nextInt();
			Logger.getInstance().log("choice = " + choice2);

			switch (choice2) {
			case 1:
				/*
				 * Adding a contact
				 */
				System.out.println("\f");
				Logger.getInstance().log("adding contact...");
				showsAddContactMenu();
				break;
			case 3:
				/*
				 * To Remove a contact validation - Check if contact exits IF contact is removed
				 * return success message or else Display"Unable to remove"
				 */
				Logger.getInstance().log("removing contact");
				phoneBookService.removeContact(phoneBookName);
				break;

			case 4:
				/*
				 * To list all contact in the current phone book by String length
				 */
				System.out.println("");
				// invoke method of model to get the collection of beans from file
				List<ContactBean> contactsArray = phoneBookService.listContacts(phoneBookName);
				// if null is returned, it means there was a problem
				if (contactsArray == null) {
					System.out.println(
							"Oops,There's a problem during listing! Contact Admin! (look at console stacktrace)");
				} else {
					// sort arraylist based on String Length
					StringLengthComparator stringLengthComparator = new StringLengthComparator();
					Collections.sort(contactsArray, stringLengthComparator);
					// loop over the list and invoke getter methods on bean to display to user
					for (ContactBean contactBean : contactsArray) {
						System.out.println(contactBean);

						// "Name : " + contactBean.getName() + " Phone : " +
						// contactBean.getPhoneNumber1());
					}
				}
				break;
			default:
				System.out.println("Yet to be implemented");
				break;
			}
		}
	}

	public static void showsAddContactMenu() {
		String result = "";
		/*
		 * Input Contact name and validate if its unique, if its unique then move
		 * forward or else ask user to enter a unique name
		 */

		System.out.println("Enter name of contact");
		contactName = stringScanner.nextLine();

		while (!phoneBookService.isContactNameUnique(phoneBookName, contactName)) {
			System.out.println("Name Already Exists, Kindly Enter a unique name.");
			contactName = stringScanner.nextLine();
			result = PhoneUtil.validateName(contactName);
		}

		/*
		 * Input Contact number and validate else ask user to enter a valid number for
		 * phoneNumber1 and phoneNumber2
		 */
		System.out.println("Enter primary phone number of contact " + contactName);
		phoneNumber1 = stringScanner.nextLine();
		result = PhoneUtil.validatePhoneNumber(phoneNumber1);
		while (!result.equals(Constants.SUCCESS)) {
			System.out.println("Enter a valid phone number");
			phoneNumber1 = stringScanner.nextLine();
			result = PhoneUtil.validatePhoneNumber(phoneNumber1);
		}

		System.out.println("Enter secondary phone number of contact " + contactName);
		phoneNumber2 = stringScanner.nextLine();
		result = PhoneUtil.validatePhoneNumber(phoneNumber2);
		while (!result.equals(Constants.SUCCESS)) {
			System.out.println("Enter a valid phone number");
			phoneNumber2 = stringScanner.nextLine();
			result = PhoneUtil.validatePhoneNumber(phoneNumber2);
		}

		/*
		 * Input comma-separated tags and validate if they start with a hash
		 */
		System.out.println("Enter  comma seperated tags for contact eg. #family,#friends,#work  " + contactName);
		dateOfBirth = stringScanner.nextLine();
		result = PhoneUtil.validateDate(dateOfBirth);
		while (!result.equals(Constants.SUCCESS)) {
			System.out.println("Enter a valid Date of Birth");
			dateOfBirth = stringScanner.nextLine();
			result = PhoneUtil.validateDate(dateOfBirth);
		}

		/*
		 * Input Date of Birth and validate if its before current date and a valid date
		 */
		System.out.println("Enter Date of birth of contact in dd/MM/yyyy format " + contactName);
		dateOfBirth = stringScanner.nextLine();
		result = PhoneUtil.validateDate(dateOfBirth);
		while (!result.equals(Constants.SUCCESS)) {
			System.out.println("Enter a valid Date of Birth");
			dateOfBirth = stringScanner.nextLine();
			result = PhoneUtil.validateDate(dateOfBirth);
		}

		/*
		 * Input address and validate each field of the address
		 */

		/*
		 * Input email1,email2,email3
		 */

		// Create bean object to hold user inputs
		ContactBean bean = new ContactBean(contactName, phoneNumber1);

		// invoke method of model to add the contact to file
		Logger.getInstance().log("main()->invoking models addContact()");
		result = phoneBookService.addContact(bean, phoneBookName);

		/*
		 * if model method returned Constants.SUCCESS, it means that addition was
		 * successful,else it is returning error message to display to user
		 */
		Logger.getInstance().log("main()-> result from addContact() " + result);
		if (result.equals(Constants.SUCCESS)) {
			System.out
					.println("Contact " + contactName + " has been added successfully to phone book " + phoneBookName);
		} else {
			System.out.println("There is a problem in adding " + result + " ,Kindly try again");
		}
	}

}
