/**
 * 
 */
package com.proinsight.erpservice.services.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proinsight.erpservice.dtos.ExamCompletedDTO;
import com.proinsight.erpservice.entities.ExamCompleted;
import com.proinsight.erpservice.entities.KnowledgeArea;
import com.proinsight.erpservice.repositories.ExamCompletedRepository;
import com.proinsight.erpservice.services.ExamCompletedService;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
@Service("examCompletedService")
public class ExamCompletedServiceImpl implements ExamCompletedService {
	
	//private fields
	@Autowired
	ExamCompletedRepository examCompletedRepository;
	@Autowired
	KnowledgeAreaServiceImpl knowledgeAreaServiceImpl;

	@Override
	public int add(ExamCompleted examCompleted) {
		System.out.println("Adding Exam Completed");
		
		//Validation
		if(examCompleted.getCandidateID() == null || examCompleted.getCandidateID().isEmpty() || examCompleted.getKnowledgeAreaID() == null || examCompleted.getKnowledgeAreaID().isEmpty() 
				|| examCompleted.getNoOfAnswers() < 0 || examCompleted.getNoOfQuestions() < 0) return 2;
		if(examCompleted.getNoOfAnswers() > examCompleted.getNoOfQuestions()) return 3;
		
		try {
			ExamCompleted eCom = examCompletedRepository.findByCandidateIDAndKnowledgeAreaID(examCompleted.getCandidateID(), examCompleted.getKnowledgeAreaID());
			if(eCom != null) return 4;
			
			examCompletedRepository.save(examCompleted);
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Adding Exam Completed "+e);
			return 0;
		}
	}

	@Override
	public int update(ExamCompleted examCompleted) {
        System.out.println("Updating Exam Completed");
		
		//Validation
		if(examCompleted.getCandidateID() == null || examCompleted.getCandidateID().isEmpty() || examCompleted.getKnowledgeAreaID() == null || examCompleted.getKnowledgeAreaID().isEmpty() 
				|| examCompleted.getNoOfAnswers() < 0 || examCompleted.getNoOfQuestions() < 0 || examCompleted.getId() == null || examCompleted.getId().isEmpty()) return 2;
		if(examCompleted.getNoOfAnswers() > examCompleted.getNoOfQuestions()) return 3;
		
		try {
			ExamCompleted eCom = examCompletedRepository.findByCandidateIDAndKnowledgeAreaID(examCompleted.getCandidateID(), examCompleted.getKnowledgeAreaID());
			if(eCom != null) {
				if(eCom.getId() != examCompleted.getId()) return 4;
			}else {
				return 5;//Cannot be null
			}
			
			examCompletedRepository.save(examCompleted);
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Updating Exam Completed "+e);
			return 0;
		}
	}

	@Override
	public List<ExamCompletedDTO> getByCandidateID(String candidateID) {
		System.out.println("Getting Candidate Exam Completed");
		
		List<ExamCompleted> exams = examCompletedRepository.findByCandidateID(candidateID);
		List<String> ids = new ArrayList<>();
		for(ExamCompleted exam : exams) {
			ids.add(exam.getKnowledgeAreaID());
		}
		
		List<KnowledgeArea> areas = knowledgeAreaServiceImpl.get(ids);
		HashMap<String, KnowledgeArea> areaMaps = new HashMap<>();
		for(KnowledgeArea area : areas) {
			areaMaps.put(area.getId(), area);
		}
		
		List<ExamCompletedDTO> examsDTO = new ArrayList<>();
		for(ExamCompleted exam : exams) {
			ExamCompletedDTO examDTO = new ExamCompletedDTO();
			examDTO.setCandidateID(exam.getCandidateID());
			examDTO.setId(exam.getId());
			examDTO.setKnowledgeArea(areaMaps.get(exam.getKnowledgeAreaID()).getName());
			examDTO.setKnowledgeAreaID(exam.getKnowledgeAreaID());
			examDTO.setNoOfAnswers(exam.getNoOfAnswers());
			examDTO.setNoOfQuestions(exam.getNoOfQuestions());
			examsDTO.add(examDTO);
		}
		
		return examsDTO;
	}

}
