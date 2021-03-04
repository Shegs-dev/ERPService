/**
 * 
 */
package com.proinsight.erpservice.services;

import java.util.List;

import com.proinsight.erpservice.entities.ScheduleHistory;

/**
 * @author OluwasegunAjayi on 4th of March 2021
 *
 */
public interface ScheduleHistoryService {
	
	//Service Methods
	int add(ScheduleHistory history);
	int update(ScheduleHistory history);
	List<ScheduleHistory> get(String scheduleTypeID, String candidateID);

}
