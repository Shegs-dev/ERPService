/**
 * 
 */
package com.proinsight.erpservice.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * @author OluwasegunAjayi on 21st of October 2020
 *
 */
@Component
public class DateConverter {
	
	//Method to get timestamp
	public long getCurrentTimestamp() {
		System.out.println("Getting Current Timestamp");
		Date date = new Date();
		return date.getTime();
	}

}
