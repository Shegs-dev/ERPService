/**
 * 
 */
package com.proinsight.erpservice.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proinsight.erpservice.entities.DocumentTemplate;
import com.proinsight.erpservice.repositories.DocumentTemplateRepository;
import com.proinsight.erpservice.services.DocumentTemplateService;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
@Service("documentTemplateService")
public class DocumentTemplateServiceImpl implements DocumentTemplateService {
	
	//private fields
	@Autowired
	DocumentTemplateRepository documentTemplateRepository;

	@Override
	public int add(DocumentTemplate template) {
		System.out.println("Adding Document Template");
		
		//Validation
		if(template.getContent() == null || template.getContent().isEmpty() || template.getTitle() == null || template.getTitle().isEmpty()) return 2;
		
		try {
			DocumentTemplate temp = documentTemplateRepository.findByTitle(template.getTitle());
			if(temp != null) return 3;
			documentTemplateRepository.save(template);
			
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Adding Document Template "+e);
			return 0;
		}
	}

	@Override
	public int update(DocumentTemplate template) {
        System.out.println("Updating Document Template");
		
		//Validation
		if(template.getContent() == null || template.getContent().isEmpty() || template.getTitle() == null || template.getTitle().isEmpty() || template.getId() == null || template.getId().isEmpty()) return 2;
		
		try {
			Optional<DocumentTemplate> temp = documentTemplateRepository.findById(template.getId());
			if(!temp.isPresent()) return 3;
			DocumentTemplate temps = documentTemplateRepository.findByTitle(template.getTitle());
			if(temps != null) {
				if(temp.get().getId() != temps.getId()) return 4;
			}
			documentTemplateRepository.save(template);
			
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Updating Document Template "+e);
			return 0;
		}
	}

	@Override
	public List<DocumentTemplate> getAll() {
		System.out.println("Getting All Document Templates");
		
		return documentTemplateRepository.findAll();
	}

}
