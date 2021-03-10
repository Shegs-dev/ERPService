/**
 * 
 */
package com.proinsight.erpservice.services;

import java.util.List;

import com.proinsight.erpservice.entities.DocumentTemplate;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
public interface DocumentTemplateService {
	
	//Service Methods
	int add(DocumentTemplate template);
	int update(DocumentTemplate template);
	List<DocumentTemplate> getAll();

}
