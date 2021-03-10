/**
 * 
 */
package com.proinsight.erpservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.proinsight.erpservice.entities.DocumentTemplate;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
@Repository("documentTemplateRepository")
public interface DocumentTemplateRepository extends MongoRepository<DocumentTemplate, String> {

	DocumentTemplate findByTitle(String title);

}
