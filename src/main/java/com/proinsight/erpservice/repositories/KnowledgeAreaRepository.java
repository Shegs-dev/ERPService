/**
 * 
 */
package com.proinsight.erpservice.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.proinsight.erpservice.entities.KnowledgeArea;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
@Repository("knowledgeAreaRepository")
public interface KnowledgeAreaRepository extends MongoRepository<KnowledgeArea, String> {

	KnowledgeArea findByName(String name);

	List<KnowledgeArea> findByIdIn(List<String> ids);

}
