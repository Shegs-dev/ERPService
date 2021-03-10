/**
 * 
 */
package com.proinsight.erpservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.proinsight.erpservice.entities.DocumentLibrary;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
@Repository("documentLibraryRepository")
public interface DocumentLibraryRepository extends MongoRepository<DocumentLibrary, String> {

}
