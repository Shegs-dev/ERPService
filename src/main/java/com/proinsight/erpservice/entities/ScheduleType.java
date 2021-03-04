/**
 * 
 */
package com.proinsight.erpservice.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author OluwasegunAjayi on 4th of March 2021
 *
 */
@Document
public class ScheduleType {
	
	//private fields
	@Id
	private String id;
	private String name;
	private String descrip;
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
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

}
