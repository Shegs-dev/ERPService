/**
 * 
 */
package com.proinsight.erpservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.proinsight.erpservice.entities.MockInterview;

/**
 * @author OluwasegunAjayi on 12th of March 2021
 *
 */
@Repository("mockInterviewRepository")
public interface MockInterviewRepository extends MongoRepository<MockInterview, String> {

	MockInterview findByCandidateID(String candidateID);

}
