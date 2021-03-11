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
import com.proinsight.erpservice.entities.PortalAccess;
import com.proinsight.erpservice.services.PortalAccessService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
@CrossOrigin(origins = "*")
@Tag(name = "Portal Access Endpoints", description = "These endpoints manages portal access on ERP")
@RestController("portalAccessRestController")
public class PortalAccessRestController {
	
	//private fields
	@Autowired
	private PortalAccessService portalAccessService;
	
	@RequestMapping(value = "/portalaccess/add", method = RequestMethod.POST)
	@Operation(description = "This Service creates new portal access on ERP")
    public ResponseEntity<?> add(@RequestBody PortalAccess access){
        System.out.println("API Call To Add New Portal Access");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = portalAccessService.add(access);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Added New Portal Access Successfully");
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			}else if(retValue == 2) {
				response.setStatus("EMPTY_TEXTFIELDS");
				response.setMessage("Fill Empty Textfield(s)");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(retValue == 3) {
				response.setStatus("ACCESS_EXIST");
				response.setMessage("Candidate Portal Access Already Exists!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else {
				response.setStatus("FAILURE");
				response.setMessage("Adding New Portal Access Failed");
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/portalaccess/update", method = RequestMethod.POST)
	@Operation(description = "This Service edits portal access on ERP")
    public ResponseEntity<?> update(@RequestBody PortalAccess access){
        System.out.println("API Call To Update Portal Access");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = portalAccessService.update(access);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Updated Portal Access Successfully");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}else if(retValue == 2) {
				response.setStatus("EMPTY_TEXTFIELDS");
				response.setMessage("Fill Empty Textfield(s)");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(retValue == 3) {
				response.setStatus("ACCESS_DISCREPANCY");
				response.setMessage("Candidate Portal Access Has Some Discrepancy!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else if(retValue == 4) {
				response.setStatus("ACCOUNT_NONEXISTS");
				response.setMessage("Candidate Account Does Not Exist!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else if(retValue == 5) {
				response.setStatus("ACCESS_IRREVOKABLE");
				response.setMessage("Candidate Has Been Granted Portal Access. To Revoke Please Contact Your Server Admin!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else {
				response.setStatus("FAILURE");
				response.setMessage("Updating Portal Access Failed");
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/portalaccess/getByCandidateID/{candidateID}", method = RequestMethod.GET)
	@Operation(description = "This Service gets candidate portal access")
    public ResponseEntity<?> getByCandidateID(@PathVariable String candidateID){
        System.out.println("API Call To Fetch Candidate Portal Access");
		
		try {
			return new ResponseEntity<>(portalAccessService.getByCandidateID(candidateID), HttpStatus.OK);
		} catch (Exception e) {
            System.err.println("Exception occurred " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

}
