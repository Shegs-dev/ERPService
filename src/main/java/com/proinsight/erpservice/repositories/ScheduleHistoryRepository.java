/**
 * 
 */
package com.proinsight.erpservice.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.proinsight.erpservice.entities.ScheduleHistory;

/**
 * @author OluwasegunAjayi on 4th of March 2021
 *
 */
@Repository
public interface ScheduleHistoryRepository extends MongoRepository<ScheduleHistory, String> {

	List<ScheduleHistory> findByCandidateIDAndScheduleTypeIDOrderByMeetingTimeAsc(String scheduleTypeID, String candidateID);

}
