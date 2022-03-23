package com.uttara.mvc.contactsApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class controls the business logic and business validations, Also called
 * the Model Layer
 * 
 * @author mariojoshuaaugustine
 */
public class PhoneBookService {

	private static Date date;
	private static SimpleDateFormat simpleDateFormat;
	/*
	 * BUSINESS LOGIC
	 */

	/**
	 * This method will open the file for the phone book given, read one line at a
	 * time, split it, inject it to a bean object add the bean to a List and finally
	 * return it.
	 * 
	 * @param	phoneBook	name
	 * @return	ArrayList of contactBean type elements
	 */
	public List<ContactBean> listContacts(String phoneBook) {

		List<ContactBean> contactArray = new ArrayList<ContactBean>();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(phoneBook + ".pb"));
			String line;
			while ((line = br.readLine()) != null) {
				String[] sa = line.split("[\\=|:]+");
				ContactBean bean = new ContactBean(sa[0], sa[1]);
				contactArray.add(bean);
			}
			return contactArray;
		} catch (IOException e) {
			e.printStackTrace();
			// we should throw a custom business exception here...for now I am returning
			// null!
			return null;
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

	/**
	 * This method will open the file if it exists or will create a new one with the
	 * phoneBook name given and will write a line with the format name:phnum using
	 * the beans data
	 * 
	 * @param-Contact bean dataholder reference is the first parameter
	 * @param-phone book name is the second parameter.
	 * @return-returns "SUCCESS" else return error message to be shown to user!
	 */
	public String addContact(ContactBean contactBean, String phoneBookName) {

		Logger.getInstance().log("model->addContact() " + contactBean + " phB " + phoneBookName);
		/*
		 * Perform business validations - check if name is already used by another as
		 * file, if its successful, apply business logic only if B.L succeeds,
		 */
		// Set Created and Modified Date
		date = new Date();
		simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String createdDate = simpleDateFormat.format(date);
		contactBean.setCreatedDate(createdDate);
		contactBean.setModifiedDate(createdDate);
		Logger.getInstance().log("createdDate=" + createdDate);
		createdDate = null;

		String line = contactBean.getName() + "=" + contactBean.getDateOfBirth() + ":" + contactBean.getPetName() + ":"
				+ contactBean.getTag() + ":" + contactBean.getAddress() + ":" + contactBean.getEmail1() + ":"
				+ contactBean.getEmail2() + ":" + contactBean.getEmail3() + ":" + contactBean.getPhoneNumber1() + ":"
				+ contactBean.getPhoneNumber2() + ":" + contactBean.getCreatedDate() + ":"
				+ contactBean.getModifiedDate();
		System.out.println("model->addContact()->line = " + line);
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(phoneBookName + ".pb", true));
			bw.write(line);
			bw.newLine();
			return Constants.SUCCESS;
		} catch (IOException e) {
			e.printStackTrace();
			return "Adding Contact Failed! " + e.getMessage();

		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (date != null) {
				date = null;
			}
			if (simpleDateFormat != null) {
				simpleDateFormat = null;
			}

		}

		// System.out.println("returning success");

	}

	/*
	 * For removing a line (a contact), you will have to copy the contents of the
	 * file temporarily to memory except the one line and then write back to the
	 * same file, overwriting the contents.
	 */
	public String removeContact(String phoneBookName) {
		return Constants.SUCCESS;
	}

	/*
	 * BUSINESS VALIDATION
	 */

	/**
	 * Check if phoneBookName exists if yes,return true, if not return false and
	 * create a new file with phoneBookName
	 * 
	 * @param
	 * @return
	 * @exception
	 */
	public boolean phoneBookExists(String phoneBookName) {
		Logger.getInstance().log("Checking if " + phoneBookName + " exists");
		return new File(phoneBookName + ".pb").exists();
	}

	/**
	 * Check if Contact name exits in that phone book, if yes return false, if no
	 * then return true
	 */
	public boolean isContactNameUnique(String phoneBookName, String contactName) {
		Logger.getInstance().log("Checking if contact name " + contactName + " exists in " + phoneBookName);

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(phoneBookName + ".pb"));
			String line;
			while ((line = br.readLine()) != null) {
				String[] contactArray = line.split(":");
				Logger.getInstance().log(contactArray[0] + ".equalsIgnoreCase " + contactName);
				if (contactArray[0].equalsIgnoreCase(contactName)) {
					Logger.getInstance().log(contactArray[0] + " == " + contactName);
					// contact name is not unique
					return false;
				}
			}
			// ContactName is unique
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			// we should throw a custom business exception here...for now I am returning
			// null!
			return true;
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

}
