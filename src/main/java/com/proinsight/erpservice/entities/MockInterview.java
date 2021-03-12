/**
 * 
 */
package com.proinsight.erpservice.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author OluwasegunAjayi on 12th of March 2021
 *
 */
@Document
public class MockInterview {
	
	//private fields
	@Id
	private String id;
	private String candidateID;
	private int firstTrial;//1 for Pass and 2 for Fail
	private int secondTrial;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCandidateID() {
		return candidateID;
	}
	public void setCandidateID(String candidateID) {
		this.candidateID = candidateID;
	}
	public int getFirstTrial() {
		return firstTrial;
	}
	public void setFirstTrial(int firstTrial) {
		this.firstTrial = firstTrial;
	}
	public int getSecondTrial() {
		return secondTrial;
	}
	public void setSecondTrial(int secondTrial) {
		this.secondTrial = secondTrial;
	}

}
