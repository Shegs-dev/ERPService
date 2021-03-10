/**
 * 
 */
package com.proinsight.erpservice.services;

import java.util.List;

import com.proinsight.erpservice.entities.KnowledgeArea;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
public interface KnowledgeAreaService {
	
	//Service Methods
	int add(KnowledgeArea area);
	int update(KnowledgeArea area);
	List<KnowledgeArea> get(List<String> ids);
	List<KnowledgeArea> gets();

}
