/**
 * 
 */
package com.proinsight.erpservice.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proinsight.erpservice.entities.PortalAccess;
import com.proinsight.erpservice.repositories.PortalAccessRepository;
import com.proinsight.erpservice.services.PortalAccessService;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
@Service("portalAccessService")
public class PortalAccessServiceImpl implements PortalAccessService {
	
	//private fields
	@Autowired
	PortalAccessRepository portalAccessRepository;

	@Override
	public int add(PortalAccess access) {
		System.out.println("Adding Portal Access");
		
		//Validation
		if(access.getCandidateID() == null || access.getCandidateID().isEmpty() || (access.getStatus() < 0 && access.getStatus() > 1)) return 2;
		
		try {
			PortalAccess acc = portalAccessRepository.findByCandidateID(access.getCandidateID());
			if(acc != null) return 3;
			portalAccessRepository.save(access);
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Adding Portal Access "+e);
			return 0;
		}
	}

	@Override
	public int update(PortalAccess access) {
        System.out.println("Updating Portal Access");
		
		//Validation
		if(access.getCandidateID() == null || access.getCandidateID().isEmpty() || (access.getStatus() < 0 && access.getStatus() > 1)) return 2;
		
		try {
			PortalAccess acc = portalAccessRepository.findByCandidateID(access.getCandidateID());
			if(acc != null) {
				if(access.getId() == null || access.getId().isEmpty()) return 3;
			}
			portalAccessRepository.save(access);
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Updating Portal Access "+e);
			return 0;
		}
	}

	@Override
	public PortalAccess getByCandidateID(String candidateID) {
		System.out.println("Getting By Candidate ID");
		
		return portalAccessRepository.findByCandidateID(candidateID);
	}

}
