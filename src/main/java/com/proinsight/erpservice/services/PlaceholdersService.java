/**
 * 
 */
package com.proinsight.erpservice.services;

import java.util.List;

import com.proinsight.erpservice.entities.Placeholders;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
public interface PlaceholdersService {
	
	//Service Methods
	int add(Placeholders placeholder);
	int update(Placeholders placeholder);
	List<Placeholders> search(String name);

}
