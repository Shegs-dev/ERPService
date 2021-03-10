/**
 * 
 */
package com.proinsight.erpservice.services;

import java.util.List;

import com.proinsight.erpservice.dtos.ExamCompletedDTO;
import com.proinsight.erpservice.entities.ExamCompleted;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
public interface ExamCompletedService {
	
	//Service Methods
	int add(ExamCompleted examCompleted);
	int update(ExamCompleted examCompleted);
	List<ExamCompletedDTO> getByCandidateID(String candidateID);

}
