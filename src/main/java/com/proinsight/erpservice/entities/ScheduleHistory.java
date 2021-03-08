/**
 * 
 */
package com.proinsight.erpservice.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author OluwasegunAjayi on the 4th of March 2021
 *
 */
@Document
public class ScheduleHistory {
	
	//private fields
	@Id
	private String id;
	private String scheduleTypeID;
	private String candidateID;
	private String adminID;
	private long meetingTime;
	private int status;//1 = both declined, 2 = admin approve, 3 = candidate approve, 4 = both approve, 5 = admin cancel, 6 = candidate cancel
	private String meetingURL;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getScheduleTypeID() {
		return scheduleTypeID;
	}
	public void setScheduleTypeID(String scheduleTypeID) {
		this.scheduleTypeID = scheduleTypeID;
	}
	public String getCandidateID() {
		return candidateID;
	}
	public void setCandidateID(String candidateID) {
		this.candidateID = candidateID;
	}
	public String getAdminID() {
		return adminID;
	}
	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}
	public long getMeetingTime() {
		return meetingTime;
	}
	public void setMeetingTime(long meetingTime) {
		this.meetingTime = meetingTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMeetingURL() {
		return meetingURL;
	}
	public void setMeetingURL(String meetingURL) {
		this.meetingURL = meetingURL;
	}

}
