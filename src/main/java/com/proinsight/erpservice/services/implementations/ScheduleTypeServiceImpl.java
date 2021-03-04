/**
 * 
 */
package com.proinsight.erpservice.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proinsight.erpservice.entities.ScheduleType;
import com.proinsight.erpservice.repositories.ScheduleTypeRepository;
import com.proinsight.erpservice.services.ScheduleTypeService;

/**
 * @author OluwasegunAjayi on 4th of March 2021
 *
 */
@Service("scheduleTypeService")
public class ScheduleTypeServiceImpl implements ScheduleTypeService {
	
	@Autowired
	ScheduleTypeRepository scheduleTypeRepository;

	@Override
	public int add(ScheduleType type) {
		System.out.println("Adding Schedule Type");
		
		//Validation
		if(type.getName() == null || type.getName().isEmpty()) return 2;
		
		try {
			ScheduleType typ = scheduleTypeRepository.findByName(type.getName());
			if(typ != null) return 3;
			scheduleTypeRepository.save(type);
			
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Adding Schedule Type "+e);
			return 0;
		}
	}

	@Override
	public int update(ScheduleType type) {
        System.out.println("Updating Schedule Type");
		
		//Validation
		if(type.getName() == null || type.getName().isEmpty() || type.getId() == null || type.getId().isEmpty()) return 2;
		
		try {
			ScheduleType typ = scheduleTypeRepository.findByName(type.getName());
			if(typ != null) {
				if(typ.getId() != type.getId()) return 3;
			}
			scheduleTypeRepository.save(type);
			
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Updating Schedule Type "+e);
			return 0;
		}
	}

	@Override
	public ScheduleType get(String id) {
		System.out.println("Getting Schedule Type");
		
		Optional<ScheduleType> type = scheduleTypeRepository.findById(id);
		if(type.isPresent()) return type.get();
		return null;
	}

	@Override
	public List<ScheduleType> getAll() {
		System.out.println("Getting All");
		
		return scheduleTypeRepository.findAll();
	}

	@Override
	public int delete(String id) {
		System.out.println("Deleting Schedule Type");
		
		try {
			ScheduleType type = this.get(id);
			if(type == null) return 2;
			scheduleTypeRepository.delete(type);
			return 1;
		}catch(Exception e) {
			System.out.println("Error While Deleting Schedule Type "+e);
			return 0;
		}
	}

}
