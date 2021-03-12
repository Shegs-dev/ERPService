/**
 * 
 */
package com.proinsight.erpservice.services;

import com.proinsight.erpservice.entities.MockInterview;

/**
 * @author OluwasegunAjayi on 12th of March 2021
 *
 */
public interface MockInterviewService {
	
	//Service Methods
	int add(MockInterview interview);
	int update(MockInterview interview);
	MockInterview get(String candidateID);

}
