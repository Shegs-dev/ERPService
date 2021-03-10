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
import com.proinsight.erpservice.entities.ExamCompleted;
import com.proinsight.erpservice.services.ExamCompletedService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
@CrossOrigin(origins = "*")
@Tag(name = "Exam Completed Endpoints", description = "These endpoints manages exam completed on ERP")
@RestController("examCompletedRestController")
public class ExamCompletedRestController {
	
	//private fields
	@Autowired
	private ExamCompletedService examCompletedService;
	
	@RequestMapping(value = "/examcompleted/add", method = RequestMethod.POST)
	@Operation(description = "This Service creates new exam completed on ERP")
    public ResponseEntity<?> add(@RequestBody ExamCompleted examCompleted){
        System.out.println("API Call To Add New Exam Completed");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = examCompletedService.add(examCompleted);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Added New Exam Completed Successfully");
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			}else if(retValue == 2) {
				response.setStatus("EMPTY_TEXTFIELDS");
				response.setMessage("Fill Empty Textfield(s)");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(retValue == 3) {
				response.setStatus("NUMBER_CONFLICT");
				response.setMessage("Number Of Answers Cannot Be Greater Than Number Of Questions!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(retValue == 4) {
				response.setStatus("EXAM_COMPLETED_EXISTS");
				response.setMessage("This Knowledge Area Has Been Added For This Candidate!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else {
				response.setStatus("FAILURE");
				response.setMessage("Adding New Exam Completed Failed");
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/examcompleted/update", method = RequestMethod.POST)
	@Operation(description = "This Service edits exam completed on ERP")
    public ResponseEntity<?> update(@RequestBody ExamCompleted examCompleted){
        System.out.println("API Call To Edit Exam Completed");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = examCompletedService.update(examCompleted);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Updated Exam Completed Successfully");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}else if(retValue == 2) {
				response.setStatus("EMPTY_TEXTFIELDS");
				response.setMessage("Fill Empty Textfield(s)");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(retValue == 3) {
				response.setStatus("NUMBER_CONFLICT");
				response.setMessage("Number Of Answers Cannot Be Greater Than Number Of Questions!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(retValue == 4) {
				response.setStatus("EXAM_COMPLETED_DISCREPANCY");
				response.setMessage("This Exam Completed Has Some Discrepancy!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else if(retValue == 5) {
				response.setStatus("EXAM_COMPLETED_NONEXISTS");
				response.setMessage("This Exam Completed Does Not Exist!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else {
				response.setStatus("FAILURE");
				response.setMessage("Updating Exam Completed Failed");
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/examcompleted/get/{candidateID}", method = RequestMethod.GET)
	@Operation(description = "This Service gets candidate exam completed")
    public ResponseEntity<List<?>> get(@PathVariable String candidateID){
        System.out.println("API Call To Fetch Exam Completed");
		
		try {
			return new ResponseEntity<>(examCompletedService.getByCandidateID(candidateID), HttpStatus.OK);
		} catch (Exception e) {
            System.err.println("Exception occurred " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

}
