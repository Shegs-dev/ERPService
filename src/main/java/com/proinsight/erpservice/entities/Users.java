/**
 * 
 */
package com.proinsight.erpservice.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author OluwasegunAjayi on 22nd of February 2021
 *
 */
@Document
public class Users {
	
	//private fields
	@Id
	private String id;
	private String fname;
	private String lname;
	private String email;
	private String phone;
	private String fullStreetAddress;
	private String apartment;//apartment or suite or unit or ect (optional)
	private String town;//or city
	private String postalCode;
	private String province;
	private String countryOrRegion;
	private int type;//1 for Candidate, 2 for Admin, and 3 for Super Admin
	private String program;
	private String username;
	private String password;
	private long createdTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFullStreetAddress() {
		return fullStreetAddress;
	}
	public void setFullStreetAddress(String fullStreetAddress) {
		this.fullStreetAddress = fullStreetAddress;
	}
	public String getApartment() {
		return apartment;
	}
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountryOrRegion() {
		return countryOrRegion;
	}
	public void setCountryOrRegion(String countryOrRegion) {
		this.countryOrRegion = countryOrRegion;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

}
