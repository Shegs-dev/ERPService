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
public class Placeholders {
	
	//private fields
	@Id
	private String id;
	private String name;
	private String ptable;
	private String pcolumn;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPtable() {
		return ptable;
	}
	public void setPtable(String ptable) {
		this.ptable = ptable;
	}
	public String getPcolumn() {
		return pcolumn;
	}
	public void setPcolumn(String pcolumn) {
		this.pcolumn = pcolumn;
	}

}
