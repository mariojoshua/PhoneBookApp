package com.uttara.mvc.contactsApp;

import java.util.Objects;

/**
 * Since address contains further dependencies, it has to have its own class 
 * with to String, hashcode and equals ovveridden.
 * @author mariojoshuaaugustine
 */

public class Address {
	private String homeNumber;
	private String streetAddress;
	private String pincode;
	private String city;
	private String state;
	private String country;

	String getHomeNumber() {
		return homeNumber;
	}

	void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	String getStreetAddress() {
		return streetAddress;
	}

	void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	String getPincode() {
		return pincode;
	}

	void setPincode(String pincode) {
		this.pincode = pincode;
	}

	String getCity() {
		return city;
	}

	void setCity(String city) {
		this.city = city;
	}

	String getState() {
		return state;
	}

	void setState(String state) {
		this.state = state;
	}

	String getCountry() {
		return country;
	}

	void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, country, homeNumber, pincode, state, streetAddress);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Address))
			return false;
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(homeNumber, other.homeNumber) && Objects.equals(pincode, other.pincode)
				&& Objects.equals(state, other.state) && Objects.equals(streetAddress, other.streetAddress);
	}

	@Override
	public String toString() {
		return "Address [homeNumber=" + homeNumber + ", streetAddress=" + streetAddress + ", pincode=" + pincode
				+ ", city=" + city + ", state=" + state + ", country=" + country + "]";
	}

}
