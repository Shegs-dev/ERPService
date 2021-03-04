/**
 * 
 */
package com.proinsight.erpservice.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proinsight.erpservice.dtos.ResponseDTO;
import com.proinsight.erpservice.entities.ScheduleHistory;
import com.proinsight.erpservice.services.ScheduleHistoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author OluwasegunAjayi on 4th of March 2021
 *
 */
@CrossOrigin(origins = "*")
@Tag(name = "Schedule History Endpoints", description = "These endpoints manages schedule history on ERP")
@RestController("scheduleHistoryRestController")
public class ScheduleHistoryRestController {
	
	@Autowired
	private ScheduleHistoryService scheduleHistoryService;
	
	@RequestMapping(value = "/schedulehistory/add", method = RequestMethod.POST)
	@Operation(description = "This Service creates new schedule history on ERP")
    public ResponseEntity<?> add(@RequestBody ScheduleHistory history){
        System.out.println("API Call To Add New Schedule History");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = scheduleHistoryService.add(history);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Added New Schedule History Successfully");
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			}else if(retValue == 2) {
				response.setStatus("EMPTY_TEXTFIELDS");
				response.setMessage("Fill Empty Textfield(s)");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(retValue == 3) {
				response.setStatus("ACCOUNT_INVALID");
				response.setMessage("User Or Admin Account Invalid!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else if(retValue == 4) {
				response.setStatus("TYPE_INVALID");
				response.setMessage("Schedule Type Invalid!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else {
				response.setStatus("FAILURE");
				response.setMessage("Adding New Schedule History Failed");
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/schedulehistory/update", method = RequestMethod.POST)
	@Operation(description = "This Service edits schedule history on ERP")
    public ResponseEntity<?> update(@RequestBody ScheduleHistory history){
        System.out.println("API Call To Edit Schedule History");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = scheduleHistoryService.update(history);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Updated Schedule History Successfully");
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			}else if(retValue == 2) {
				response.setStatus("EMPTY_TEXTFIELDS");
				response.setMessage("Fill Empty Textfield(s)");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(retValue == 3) {
				response.setStatus("ACCOUNT_INVALID");
				response.setMessage("Schedule History Or User Or Admin Account Invalid!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else if(retValue == 4) {
				response.setStatus("TYPE_INVALID");
				response.setMessage("Schedule Type Invalid!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else {
				response.setStatus("FAILURE");
				response.setMessage("Updating Schedule History Failed");
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/schedulehistory/gets/{scheduleTypeID}/{candidateID}", method = RequestMethod.GET)
	@Operation(description = "This Service gets all schedule history by candidate and schedule type")
    public ResponseEntity<List<?>> gets(@PathVariable String scheduleTypeID, @PathVariable String candidateID){
        System.out.println("API Call To Fetch All Schedule History By Candidate And Schedule Type");
		
		try {
			return new ResponseEntity<>(scheduleHistoryService.get(scheduleTypeID, candidateID), HttpStatus.OK);
		} catch (Exception e) {
            System.err.println("Exception occurred " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

}
