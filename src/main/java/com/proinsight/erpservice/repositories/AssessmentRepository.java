/**
 * 
 */
package com.proinsight.erpservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.proinsight.erpservice.entities.Assessment;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
@Repository("assessmentRepository")
public interface AssessmentRepository extends MongoRepository<Assessment, String> {

}
