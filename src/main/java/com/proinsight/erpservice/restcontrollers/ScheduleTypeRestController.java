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
import com.proinsight.erpservice.entities.ScheduleType;
import com.proinsight.erpservice.services.ScheduleTypeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author OluwasegunAjayi on 4th of March 2021
 *
 */
@CrossOrigin(origins = "*")
@Tag(name = "Schedule Type Endpoints", description = "These endpoints manages schedule types on ERP")
@RestController("scheduleTypeRestController")
public class ScheduleTypeRestController {
	
	@Autowired
	private ScheduleTypeService scheduleTypeService;
	
	@RequestMapping(value = "/scheduletype/add", method = RequestMethod.POST)
	@Operation(description = "This Service creates new schedule type on ERP")
    public ResponseEntity<?> add(@RequestBody ScheduleType type){
        System.out.println("API Call To Create New Schedule Type");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = scheduleTypeService.add(type);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Added New Schedule Type Successfully");
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			}else if(retValue == 2) {
				response.setStatus("EMPTY_TEXTFIELDS");
				response.setMessage("Fill Empty Textfield(s)");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(retValue == 3) {
				response.setStatus("SCHEDULE_TYPE_EXISTS");
				response.setMessage("Schedule Type Already Exists!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else {
				response.setStatus("FAILURE");
				response.setMessage("Adding New Schedule Type Failed");
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/scheduletype/update", method = RequestMethod.POST)
	@Operation(description = "This Service edit a schedule type on ERP")
    public ResponseEntity<?> update(@RequestBody ScheduleType type){
        System.out.println("API Call To Edit Schedule Type");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = scheduleTypeService.update(type);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Updated Schedule Type Successfully");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}else if(retValue == 2) {
				response.setStatus("EMPTY_TEXTFIELDS");
				response.setMessage("Fill Empty Textfield(s)");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(retValue == 3) {
				response.setStatus("SCHEDULE_TYPE_DISCREPANCY");
				response.setMessage("Schedule Type Has Some Discrepancies!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else {
				response.setStatus("FAILURE");
				response.setMessage("Updating Schedule Type Failed");
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/scheduletype/get/{id}", method = RequestMethod.GET)
	@Operation(description = "This Service gets particular schedule type")
    public ResponseEntity<?> get(@PathVariable String id){
        System.out.println("API Call To Fetch Particular Schedule Type");
		
		try {
			return new ResponseEntity<>(scheduleTypeService.get(id), HttpStatus.OK);
		} catch (Exception e) {
            System.err.println("Exception occurred " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@RequestMapping(value = "/scheduletype/gets", method = RequestMethod.GET)
	@Operation(description = "This Service gets all schedule types")
    public ResponseEntity<List<?>> gets(){
        System.out.println("API Call To Fetch All Schedule Types");
		
		try {
			return new ResponseEntity<>(scheduleTypeService.getAll(), HttpStatus.OK);
		} catch (Exception e) {
            System.err.println("Exception occurred " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@RequestMapping(value = "/scheduletype/delete/{id}", method = RequestMethod.DELETE)
	@Operation(description = "This Service deletes a schedule type on ERP")
    public ResponseEntity<?> delete(@PathVariable String id){
        System.out.println("API Call To Delete Schedule Type");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = scheduleTypeService.delete(id);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Deleted Schedule Type Successfully");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}else if(retValue == 2) {
				response.setStatus("SCHEDULE_TYPE_NONEXISTS");
				response.setMessage("Schedule Type Does Not Exist");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else {
				response.setStatus("FAILURE");
				response.setMessage("Deleting Schedule Type Failed");
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
