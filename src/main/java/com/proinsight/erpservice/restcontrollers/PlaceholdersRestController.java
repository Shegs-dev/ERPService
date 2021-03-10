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
import com.proinsight.erpservice.entities.Placeholders;
import com.proinsight.erpservice.services.PlaceholdersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author OluwasegunAjayi on the 9th of March 2021
 *
 */
@CrossOrigin(origins = "*")
@Tag(name = "Placeholders Endpoints", description = "These endpoints manages placeholders on ERP")
@RestController("placeholdersRestController")
public class PlaceholdersRestController {
	
	//private fields
	@Autowired
	private PlaceholdersService placeholdersService;
	
	@RequestMapping(value = "/placeholders/add", method = RequestMethod.POST)
	@Operation(description = "This Service creates new placeholder on ERP")
    public ResponseEntity<?> add(@RequestBody Placeholders placeholder){
        System.out.println("API Call To Add New Placeholder");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = placeholdersService.add(placeholder);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Added New Placeholder Successfully");
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			}else if(retValue == 2) {
				response.setStatus("EMPTY_TEXTFIELDS");
				response.setMessage("Fill Empty Textfield(s)");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(retValue == 3) {
				response.setStatus("PLACEHOLDER_EXISTS");
				response.setMessage("Placeholder Already Exists!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else {
				response.setStatus("FAILURE");
				response.setMessage("Adding New Placeholder Failed");
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/placeholders/update", method = RequestMethod.POST)
	@Operation(description = "This Service edits placeholder on ERP")
    public ResponseEntity<?> update(@RequestBody Placeholders placeholder){
        System.out.println("API Call To Edit Placeholder");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = placeholdersService.update(placeholder);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Updated Placeholder Successfully");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}else if(retValue == 2) {
				response.setStatus("EMPTY_TEXTFIELDS");
				response.setMessage("Fill Empty Textfield(s)");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(retValue == 3) {
				response.setStatus("PLACEHOLDER_UPDATE_IMPOSSIBLE");
				response.setMessage("This Placeholder Has Nothing To Update!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else if(retValue == 4) {
				response.setStatus("PLACEHOLDER_NONEXIST");
				response.setMessage("This Placeholder Does Not Exist!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else {
				response.setStatus("FAILURE");
				response.setMessage("Updating Placeholder Failed");
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/placeholders/search/{name}", method = RequestMethod.GET)
	@Operation(description = "This Service search for placeholder")
    public ResponseEntity<List<?>> search(@PathVariable String name){
        System.out.println("API Call To Search Placeholder");
		
		try {
			return new ResponseEntity<>(placeholdersService.search(name), HttpStatus.OK);
		} catch (Exception e) {
            System.err.println("Exception occurred " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

}
