/**
 * 
 */
package com.proinsight.erpservice.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
@Document
public class PortalAccess {
	
	//private fields
	@Id
	private String id;
	private int status; //0 for Not Granted, 1 for Granted
	private String candidateID;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCandidateID() {
		return candidateID;
	}
	public void setCandidateID(String candidateID) {
		this.candidateID = candidateID;
	}

}
