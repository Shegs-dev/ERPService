/**
 * 
 */
package com.proinsight.erpservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.proinsight.erpservice.entities.PortalAccess;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
@Repository("portalAccessRepository")
public interface PortalAccessRepository extends MongoRepository<PortalAccess, String> {

	PortalAccess findByCandidateID(String candidateID);

}
