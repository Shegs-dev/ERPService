/**
 * 
 */
package com.proinsight.erpservice.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.proinsight.erpservice.entities.Placeholders;

/**
 * @author OluwasegunAjayi on the 9th of March 2021
 *
 */
@Repository("placeholderRepository")
public interface PlaceholdersRepository extends MongoRepository<Placeholders, String> {

	Placeholders findByPcolumnAndPtable(String pcolumn, String ptable);

	List<Placeholders> findByNameContaining(String name);

}
