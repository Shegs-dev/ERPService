/**
 * 
 */
package com.proinsight.erpservice.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proinsight.erpservice.entities.Placeholders;
import com.proinsight.erpservice.repositories.PlaceholdersRepository;
import com.proinsight.erpservice.services.PlaceholdersService;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
@Service("placeholdersService")
public class PlaceholdersServiceImpl implements PlaceholdersService {
	
	//private fields
	@Autowired
	PlaceholdersRepository placeholdersRepository;

	@Override
	public int add(Placeholders placeholder) {
		System.out.println("Adding Placeholder");
		
		//Validation
		if(placeholder.getPcolumn() == null || placeholder.getPcolumn().isEmpty() || placeholder.getPtable() == null || placeholder.getPtable().isEmpty()) return 2;
		
		try {
			Placeholders pHolder = placeholdersRepository.findByPcolumnAndPtable(placeholder.getPcolumn(), placeholder.getPtable());
			if(pHolder != null) return 3;
			placeholder.setName(placeholder.getPtable() + "_" + placeholder.getPcolumn());
			placeholdersRepository.save(placeholder);
			
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Adding Placeholder "+e);
			return 0;
		}
	}

	@Override
	public int update(Placeholders placeholder) {
        System.out.println("Updating Placeholder");
		
		//Validation
		if(placeholder.getPcolumn() == null || placeholder.getPcolumn().isEmpty() || placeholder.getPtable() == null || placeholder.getPtable().isEmpty() || placeholder.getId() == null ||
				placeholder.getId().isEmpty()) return 2;
		
		try {
			Placeholders pHolder = placeholdersRepository.findByPcolumnAndPtable(placeholder.getPcolumn(), placeholder.getPtable());
			if(pHolder != null) return 3;
			else {
				Optional<Placeholders> pHold = placeholdersRepository.findById(placeholder.getId());
				if(!pHold.isPresent()) return 4;
			}
			placeholder.setName(placeholder.getPtable() + "_" + placeholder.getPcolumn());
			placeholdersRepository.save(placeholder);
			
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Updating Placeholder "+e);
			return 0;
		}
	}

	@Override
	public List<Placeholders> search(String name) {
		System.out.println("Search Placeholder");
		
		return placeholdersRepository.findByNameContaining(name);
	}

}
