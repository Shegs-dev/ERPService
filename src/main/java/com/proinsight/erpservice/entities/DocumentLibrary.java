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
public class DocumentLibrary {
	
	//private fields
	@Id
	private String id;
	private String candidateID;
	private String docTemplateID;
	private String name; 
	private String downloadURI;
	private String displayURI;
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
	public String getDocTemplateID() {
		return docTemplateID;
	}
	public void setDocTemplateID(String docTemplateID) {
		this.docTemplateID = docTemplateID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDownloadURI() {
		return downloadURI;
	}
	public void setDownloadURI(String downloadURI) {
		this.downloadURI = downloadURI;
	}
	public String getDisplayURI() {
		return displayURI;
	}
	public void setDisplayURI(String displayURI) {
		this.displayURI = displayURI;
	}

}
