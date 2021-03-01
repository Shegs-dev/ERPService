/**
 * 
 */
package com.proinsight.erpservice.dtos;

/**
 * @author OluwasegunAjayi on 25th of February 2021
 *
 */
public class UserFilterDTO {
	
	//private fields
	private String fname;
	private String lname;
	private String email;
	private String program;
	private int type;
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
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

}
