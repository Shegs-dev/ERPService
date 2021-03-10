/**
 * 
 */
package com.proinsight.erpservice.dtos;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
public class ExamCompletedDTO {
	
	//private fields
	private String id;
	private String knowledgeAreaID;
	private String knowledgeArea;
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
	public String getKnowledgeArea() {
		return knowledgeArea;
	}
	public void setKnowledgeArea(String knowledgeArea) {
		this.knowledgeArea = knowledgeArea;
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
