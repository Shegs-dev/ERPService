/**
 * 
 */
package com.proinsight.erpservice.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proinsight.erpservice.entities.KnowledgeArea;
import com.proinsight.erpservice.repositories.KnowledgeAreaRepository;
import com.proinsight.erpservice.services.KnowledgeAreaService;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
@Service("knowledgeAreaService")
public class KnowledgeAreaServiceImpl implements KnowledgeAreaService {
	
	@Autowired
	KnowledgeAreaRepository knowledgeAreaRepository;

	@Override
	public int add(KnowledgeArea area) {
		System.out.println("Adding Knowledge Area");
		
		//Validation
		if(area.getName() == null || area.getName().isEmpty()) return 2;
		
		try {
			KnowledgeArea karea = knowledgeAreaRepository.findByName(area.getName());
			if(karea != null) return 3;
			
			knowledgeAreaRepository.save(area);
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Adding Knowledge Area "+e);
			return 0;
		}
	}

	@Override
	public int update(KnowledgeArea area) {
        System.out.println("Updating Knowledge Area");
		
		//Validation
		if(area.getName() == null || area.getName().isEmpty() || area.getId() == null || area.getId().isEmpty()) return 2;
		
		try {
			KnowledgeArea karea = knowledgeAreaRepository.findByName(area.getName());
			if(karea == null) {
				Optional<KnowledgeArea> kea = knowledgeAreaRepository.findById(area.getId());
				if(!kea.isPresent()) return 3;
			}else {
				return 4; //cannot make update if name already exists
			}
			
			knowledgeAreaRepository.save(area);
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Updating Knowledge Area "+e);
			return 0;
		}
	}

	@Override
	public List<KnowledgeArea> get(List<String> ids) {
		System.out.println("Getting Knowledge Area");
		
		return knowledgeAreaRepository.findByIdIn(ids);
	}

	@Override
	public List<KnowledgeArea> gets() {
		System.out.println("Getting All Knowledge Areas");
		
		return knowledgeAreaRepository.findAll();
	}

}
