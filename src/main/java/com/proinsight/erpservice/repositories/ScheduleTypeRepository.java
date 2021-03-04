/**
 * 
 */
package com.proinsight.erpservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.proinsight.erpservice.entities.ScheduleType;

/**
 * @author OluwasegunAjayi on 4thh of March 2021
 *
 */
@Repository
public interface ScheduleTypeRepository extends MongoRepository<ScheduleType, String> {

	ScheduleType findByName(String name);

}
