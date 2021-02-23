/**
 * 
 */
package com.proinsight.erpservice.dtos;

/**
 * @author OluwasegunAjayi on 22nd of February 2021
 *
 */
public class ResponseDTO {
	
	//private fields
	private String status;
	private String message;
	private Object data;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
