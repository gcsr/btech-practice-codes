/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Password;

import java.io.Serializable;

public class User implements Serializable {

	private String firstname;
	private String lastname;
	private String phone;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String email;
	private String password;

	public User() {
		firstname = "";
		lastname = "";
		phone = "";
		address = "";
		city = "";
		state = "";
		zipcode = "";
		email = "";
		password = "welcome1";
	}

	public User(String Firstname, String Lastname, String Phone, String Address, String City, String State, String Zipcode, String Email) {
		this();
		this.firstname = Firstname;
		this.lastname = Lastname;
		this.phone = Phone;
		this.address = Address;
		this.city = City;
		this.state = State;
		this.zipcode = Zipcode;
		this.email = Email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String Firstname) {
		this.firstname = Firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String Lastname) {
		this.lastname = Lastname;

	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String Phone) {
		this.lastname = Phone;

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String Address) {
		this.address = Address;

	}

	public String getCity() {
		return city;
	}

	public void setCity(String City) {
		this.city = City;

	}

	public String getState() {
		return state;
	}

	public void setState(String State) {
		this.state = State;

	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String Zipcode) {
		this.zipcode = Zipcode;

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String Email) {
		this.email = Email;
	}

	/**
	 * @return the Username
	 */
	public String getUsername() {
		return this.lastname + this.zipcode;
	}

	/**
	 * @return the Password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param Password the Password to set
	 */
	public void setPassword(String Password) {
		this.password = Password;
	}
}
