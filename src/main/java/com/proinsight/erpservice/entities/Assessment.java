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
public class Assessment {
	
	//private fields
	@Id
	private String id;
	private String status; //0 for Fail and 1 for Pass
	private String documentLibraryID;
	private String candidateID;
	private String contractStatus;//0 for Pending and 1 for Signed and Received
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDocumentLibraryID() {
		return documentLibraryID;
	}
	public void setDocumentLibraryID(String documentLibraryID) {
		this.documentLibraryID = documentLibraryID;
	}
	public String getCandidateID() {
		return candidateID;
	}
	public void setCandidateID(String candidateID) {
		this.candidateID = candidateID;
	}
	public String getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

}
