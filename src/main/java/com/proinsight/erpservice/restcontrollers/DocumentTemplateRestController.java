/**
 * 
 */
package com.proinsight.erpservice.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proinsight.erpservice.dtos.ResponseDTO;
import com.proinsight.erpservice.entities.DocumentTemplate;
import com.proinsight.erpservice.services.DocumentTemplateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
@CrossOrigin(origins = "*")
@Tag(name = "Document Template Endpoints", description = "These endpoints manages document templates on ERP")
@RestController("documentTemplateRestController")
public class DocumentTemplateRestController {
	
	//private fields
	@Autowired
	private DocumentTemplateService documentTemplateService;
	
	@RequestMapping(value = "/documenttemplate/add", method = RequestMethod.POST)
	@Operation(description = "This Service creates new document tenplate on ERP")
    public ResponseEntity<?> add(@RequestBody DocumentTemplate template){
        System.out.println("API Call To Add New Document Template");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = documentTemplateService.add(template);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Added New Document Template Successfully");
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			}else if(retValue == 2) {
				response.setStatus("EMPTY_TEXTFIELDS");
				response.setMessage("Fill Empty Textfield(s)");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(retValue == 3) {
				response.setStatus("TEMPLATE_EXISTS");
				response.setMessage("Document Template Already Exists!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else {
				response.setStatus("FAILURE");
				response.setMessage("Adding New Document Template Failed");
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/documenttemplate/update", method = RequestMethod.POST)
	@Operation(description = "This Service edits a document template on ERP")
    public ResponseEntity<?> update(@RequestBody DocumentTemplate template){
        System.out.println("API Call To Edit Document Template");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = documentTemplateService.update(template);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Updated Document Template Successfully");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}else if(retValue == 2) {
				response.setStatus("EMPTY_TEXTFIELDS");
				response.setMessage("Fill Empty Textfield(s)");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(retValue == 3) {
				response.setStatus("TEMPLATE_NONEXISTS");
				response.setMessage("Document Template Does Not Exists!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else if(retValue == 4) {
				response.setStatus("TEMPLATE_DISCREPANCY");
				response.setMessage("Document Template Has Some Discrepancy!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else {
				response.setStatus("FAILURE");
				response.setMessage("Updating Document Template Failed");
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/documenttemplate/gets", method = RequestMethod.GET)
	@Operation(description = "This Service gets all document templates")
    public ResponseEntity<List<?>> gets(){
        System.out.println("API Call To Fetch All Document Templates");
		
		try {
			return new ResponseEntity<>(documentTemplateService.getAll(), HttpStatus.OK);
		} catch (Exception e) {
            System.err.println("Exception occurred " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

}
