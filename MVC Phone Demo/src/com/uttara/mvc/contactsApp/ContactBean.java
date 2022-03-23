package com.uttara.mvc.contactsApp;

import java.util.Objects;

/**
 * This is a data holder class. This class has instance variables with
 * setter/getters and a convenience constructor to set the state. Since this
 * class has state, we override equals(), hashCode(), toString() so that objects
 * of this class behave correctly if put into collections!
 * 
 * @author mariojoshuaaugustine
 * 
 */
public class ContactBean {

	private String name;
	private String phoneNumber1;
	private String phoneNumber2;
	private String dateOfBirth;
	private String petName;
	private Address address;
	private String email1;
	private String email2;
	private String email3;
	private String createdDate;
	private String modifiedDate;
	private String tag;

	/*
	 * Constructors
	 */
	public ContactBean() {
		// TODO Auto-generated constructor stub
	}

	public ContactBean(String name, String phoneNumber) {
		super();
		this.name = name;
		this.phoneNumber1 = phoneNumber;
	}

	ContactBean(String name, String phoneNumber1, String phoneNumber2, String dateOfBirth, String petName,
			Address address, String email1, String email2, String email3, String tag) {
		super();
		this.name = name;
		this.phoneNumber1 = phoneNumber1;
		this.phoneNumber2 = phoneNumber2;
		this.dateOfBirth = dateOfBirth;
		this.petName = petName;
		this.address = address;
		this.email1 = email1;
		this.email2 = email2;
		this.email3 = email3;
		this.tag = tag;
	}

	/*
	 * Hashcode
	 */
	@Override
	public int hashCode() {
		return Objects.hash(address, createdDate, dateOfBirth, email1, email2, email3, modifiedDate, name, petName,
				phoneNumber1, phoneNumber2, tag);
	}

	/*
	 * Equals
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ContactBean))
			return false;
		ContactBean other = (ContactBean) obj;
		return Objects.equals(address, other.address) && Objects.equals(createdDate, other.createdDate)
				&& Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(email1, other.email1)
				&& Objects.equals(email2, other.email2) && Objects.equals(email3, other.email3)
				&& Objects.equals(modifiedDate, other.modifiedDate) && Objects.equals(name, other.name)
				&& Objects.equals(petName, other.petName) && Objects.equals(phoneNumber1, other.phoneNumber1)
				&& Objects.equals(phoneNumber2, other.phoneNumber2) && Objects.equals(tag, other.tag);
	}

	/*
	 * toString
	 */
	@Override
	public String toString() {
		return "ContactBean [name=" + name + ", phoneNumber1=" + phoneNumber1 + ", phoneNumber2=" + phoneNumber2
				+ ", dateOfBirth=" + dateOfBirth + ", petName=" + petName + ", address=" + address + ", email1="
				+ email1 + ", email2=" + email2 + ", email3=" + email3 + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", tag=" + tag + "]";
	}

	/**
	 * Setters and Getters
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	String getDateOfBirth() {
		return dateOfBirth;
	}

	void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	String getPetName() {
		return petName;
	}

	void setPetName(String petName) {
		this.petName = petName;
	}

	Address getAddress() {
		return address;
	}

	void setAddress(Address address) {
		this.address = address;
	}

	String getEmail1() {
		return email1;
	}

	void setEmail1(String email1) {
		this.email1 = email1;
	}

	String getEmail2() {
		return email2;
	}

	void setEmail2(String email2) {
		this.email2 = email2;
	}

	String getEmail3() {
		return email3;
	}

	void setEmail3(String email3) {
		this.email3 = email3;
	}

	String getCreatedDate() {
		return createdDate;
	}

	void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	String getModifiedDate() {
		return modifiedDate;
	}

	void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	String getTag() {
		return tag;
	}

	void setTag(String tag) {
		this.tag = tag;
	}

	String getPhoneNumber1() {
		return phoneNumber1;
	}

	void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	String getPhoneNumber2() {
		return phoneNumber2;
	}

	void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}


}
