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
public class ExamCompleted {
	
	//private fields
	@Id
	private String id;
	private String knowledgeAreaID;
	private long noOfQuestions;
	private long noOfAnswers;
	private String candidateID;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKnowledgeAreaID() {
		return knowledgeAreaID;
	}
	public void setKnowledgeAreaID(String knowledgeAreaID) {
		this.knowledgeAreaID = knowledgeAreaID;
	}
	public long getNoOfQuestions() {
		return noOfQuestions;
	}
	public void setNoOfQuestions(long noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}
	public long getNoOfAnswers() {
		return noOfAnswers;
	}
	public void setNoOfAnswers(long noOfAnswers) {
		this.noOfAnswers = noOfAnswers;
	}
	public String getCandidateID() {
		return candidateID;
	}
	public void setCandidateID(String candidateID) {
		this.candidateID = candidateID;
	}

}
