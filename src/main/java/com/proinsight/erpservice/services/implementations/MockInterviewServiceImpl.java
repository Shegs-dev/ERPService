/**
 * 
 */
package com.proinsight.erpservice.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proinsight.erpservice.entities.MockInterview;
import com.proinsight.erpservice.repositories.MockInterviewRepository;
import com.proinsight.erpservice.services.MockInterviewService;

/**
 * @author OluwasegunAjayi on 12th of March 2021
 *
 */
@Service("mockInterviewService")
public class MockInterviewServiceImpl implements MockInterviewService {
	
	//private fields
	@Autowired
	private MockInterviewRepository mockInterviewRepository;

	@Override
	public int add(MockInterview interview) {
		System.out.println("Adding Mock Interview");
		
		//Validation
		if(interview.getCandidateID() == null || interview.getCandidateID().isEmpty() || interview.getFirstTrial() == 0) return 2;
		
		try {
			MockInterview inview = mockInterviewRepository.findByCandidateID(interview.getCandidateID());
			if(inview != null) return 3;
			
			mockInterviewRepository.save(interview);
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Adding Mock Interview "+e);
			return 0;
		}
	}

	@Override
	public int update(MockInterview interview) {
        System.out.println("Updating Mock Interview");
		
		//Validation
		if(interview.getCandidateID() == null || interview.getCandidateID().isEmpty() || interview.getFirstTrial() == 0 || interview.getId() == null || interview.getId().isEmpty() 
            || interview.getSecondTrial() == 0) return 2;
		
		try {
			MockInterview inview = mockInterviewRepository.findByCandidateID(interview.getCandidateID());
			if(inview == null) return 3;
			else {
				if(inview.getId() != interview.getId()) return 4;
				if(inview.getFirstTrial() == 0) return 5;
			}
			
			mockInterviewRepository.save(interview);
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Updating Mock Interview "+e);
			return 0;
		}
	}

	@Override
	public MockInterview get(String candidateID) {
		System.out.println("Getting Mock Interview");
		
		return mockInterviewRepository.findByCandidateID(candidateID);
	}

}
