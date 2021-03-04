/**
 * 
 */
package com.proinsight.erpservice.services;

import java.util.List;

import com.proinsight.erpservice.entities.ScheduleType;

/**
 * @author OluwasegunAjayi on 4th of March 2021
 *
 */
public interface ScheduleTypeService {
	
	//Service Methods
	int add(ScheduleType type);
	int update(ScheduleType type);
	ScheduleType get(String id);
	List<ScheduleType> getAll();
	int delete(String id);

}
