/**
 * 
 */
package com.proinsight.erpservice.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.proinsight.erpservice.entities.ExamCompleted;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
@Repository("examCompletedRepository")
public interface ExamCompletedRepository extends MongoRepository<ExamCompleted, String> {

	ExamCompleted findByCandidateIDAndKnowledgeAreaID(String candidateID, String knowledgeAreaID);

	List<ExamCompleted> findByCandidateID(String candidateID);

}
