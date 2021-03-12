/**
 * 
 */
package com.proinsight.erpservice.restcontrollers;

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
import com.proinsight.erpservice.entities.MockInterview;
import com.proinsight.erpservice.services.MockInterviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author OluwasegunAjayi on the 12th of March 2021
 *
 */
@CrossOrigin(origins = "*")
@Tag(name = "Mock Interview Endpoints", description = "These endpoints manages mock interview on ERP")
@RestController("mockInterviewRestController")
public class MockInterviewRestController {
	
	//private fields
	@Autowired
	private MockInterviewService mockInterviewService;
	
	@RequestMapping(value = "/mockinterview/add", method = RequestMethod.POST)
	@Operation(description = "This Service creates new mock interview on ERP")
    public ResponseEntity<?> add(@RequestBody MockInterview interview){
        System.out.println("API Call To Add New Mock Interview");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = mockInterviewService.add(interview);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Added New Mock Interview Successfully");
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			}else if(retValue == 2) {
				response.setStatus("EMPTY_TEXTFIELDS");
				response.setMessage("Fill Empty Textfield(s)");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(retValue == 3) {
				response.setStatus("MOCK_INTERVIEW_EXIST");
				response.setMessage("Candidate Already Had A Mock Interview!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else {
				response.setStatus("FAILURE");
				response.setMessage("Adding New Mock Interview Failed");
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/mockinterview/update", method = RequestMethod.POST)
	@Operation(description = "This Service edits a mock interview on ERP")
    public ResponseEntity<?> update(@RequestBody MockInterview interview){
        System.out.println("API Call To Edit Mock Interview");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = mockInterviewService.update(interview);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Updated Mock Interview Successfully");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}else if(retValue == 2) {
				response.setStatus("EMPTY_TEXTFIELDS");
				response.setMessage("Fill Empty Textfield(s)");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(retValue == 3) {
				response.setStatus("MOCK_INTERVIEW_NONEXIST");
				response.setMessage("Candidate Does Not Have A Mock Interview. You Need To Create One First!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else if(retValue == 4) {
				response.setStatus("MOCK_INTERVIEW_DISCREPANCY");
				response.setMessage("This Mock Interview Has Some Discrepancy!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else if(retValue == 5) {
				response.setStatus("FIRST_TRIAL_ISSUE");
				response.setMessage("This Mock Interview Has No First Trial. You Have To Do That First!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else {
				response.setStatus("FAILURE");
				response.setMessage("Updating Mock Interview Failed");
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/mockinterview/get/{candidateID}", method = RequestMethod.GET)
	@Operation(description = "This Service gets mock interview by candidate")
    public ResponseEntity<?> get(@PathVariable String candidateID){
        System.out.println("API Call To Fetch Candidate Mock Interview");
		
		try {
			return new ResponseEntity<>(mockInterviewService.get(candidateID), HttpStatus.OK);
		} catch (Exception e) {
            System.err.println("Exception occurred " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

}
