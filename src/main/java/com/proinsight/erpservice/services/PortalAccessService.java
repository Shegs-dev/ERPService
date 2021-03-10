/**
 * 
 */
package com.proinsight.erpservice.services;

import com.proinsight.erpservice.entities.PortalAccess;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
public interface PortalAccessService {
	
	//Service Methods
	int add(PortalAccess access);
	int update(PortalAccess access);
	PortalAccess getByCandidateID(String candidateID);

}
