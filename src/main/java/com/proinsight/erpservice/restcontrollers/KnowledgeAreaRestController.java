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
import com.proinsight.erpservice.entities.KnowledgeArea;
import com.proinsight.erpservice.services.KnowledgeAreaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author OluwasegunAjayi on 9th of March 2021
 *
 */
@CrossOrigin(origins = "*")
@Tag(name = "Knowledge Area Endpoints", description = "These endpoints manages knowledge area on ERP")
@RestController("knowledgeAreaRestController")
public class KnowledgeAreaRestController {
	
	//private fields
	@Autowired
	private KnowledgeAreaService knowledgeAreaService;
	
	@RequestMapping(value = "/knowledgearea/add", method = RequestMethod.POST)
	@Operation(description = "This Service creates new knowledge area on ERP")
    public ResponseEntity<?> add(@RequestBody KnowledgeArea area){
        System.out.println("API Call To Add New Knowledge Area");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = knowledgeAreaService.add(area);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Added New Knowledge Area Successfully");
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			}else if(retValue == 2) {
				response.setStatus("EMPTY_TEXTFIELDS");
				response.setMessage("Fill Empty Textfield(s)");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(retValue == 3) {
				response.setStatus("KNOWLEDGE_AREA_EXIST");
				response.setMessage("Knowledge Area Already Exists!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else {
				response.setStatus("FAILURE");
				response.setMessage("Adding New Knowledge Area Failed");
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/knowledgearea/update", method = RequestMethod.POST)
	@Operation(description = "This Service edits knowledge area on ERP")
    public ResponseEntity<?> update(@RequestBody KnowledgeArea area){
        System.out.println("API Call To Edit Knowledge Area");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = knowledgeAreaService.update(area);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Updated Knowledge Area Successfully");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}else if(retValue == 2) {
				response.setStatus("EMPTY_TEXTFIELDS");
				response.setMessage("Fill Empty Textfield(s)");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(retValue == 3) {
				response.setStatus("KNOWLEDGE_AREA_NONEXIST");
				response.setMessage("Knowledge Area Does Not Exist!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else if(retValue == 4) {
				response.setStatus("UPDATE_IMPOSSIBLE");
				response.setMessage("Knowledge Area Cannot Be Updated As Name Already Exist!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else {
				response.setStatus("FAILURE");
				response.setMessage("Updating Knowledge Area Failed");
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/knowledgearea/get/{ids}", method = RequestMethod.GET)
	@Operation(description = "This Service gets knowledge area by ids")
    public ResponseEntity<List<?>> get(@PathVariable List<String> ids){
        System.out.println("API Call To Fetch Knowledge Areas By IDs");
		
		try {
			return new ResponseEntity<>(knowledgeAreaService.get(ids), HttpStatus.OK);
		} catch (Exception e) {
            System.err.println("Exception occurred " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@RequestMapping(value = "/knowledgearea/gets", method = RequestMethod.GET)
	@Operation(description = "This Service gets all knowledge area")
    public ResponseEntity<List<?>> gets(){
        System.out.println("API Call To Fetch All Knowledge Areas");
		
		try {
			return new ResponseEntity<>(knowledgeAreaService.gets(), HttpStatus.OK);
		} catch (Exception e) {
            System.err.println("Exception occurred " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

}
