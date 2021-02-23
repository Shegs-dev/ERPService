/**
 * 
 */
package com.proinsight.erpservice.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proinsight.erpservice.dtos.LoginDTO;
import com.proinsight.erpservice.dtos.ResponseDTO;
import com.proinsight.erpservice.entities.Users;
import com.proinsight.erpservice.services.UsersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author OluwasegunAjayi on 22nd of February 2021
 *
 */
@CrossOrigin(origins = "*")
@Tag(name = "Users Endpoints", description = "These endpoints manages users on ERP")
@RestController("usersRestController")
public class UsersRestController {
	
	//private fields
	@Autowired
	private UsersService usersService;
	
	@RequestMapping(value = "/users/add", method = RequestMethod.POST)
	@Operation(description = "This Service adds new user on ERP")
    public ResponseEntity<?> add(@RequestBody Users user) {
		System.out.println("API Call To Add New User");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = usersService.add(user);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Added New User Successfully");
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			}else if(retValue == 2) {
				response.setStatus("EMPTY_TEXTFIELDS");
				response.setMessage("Fill Empty Textfield(s)");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(retValue == 3) {
				response.setStatus("INVALID_USER_TYPE");
				response.setMessage("User Type Invalid");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else if(retValue == 4) {
				response.setStatus("ACCOUNT_EXISTS");
				response.setMessage("User Account Already Exists!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else {
				response.setStatus("FAILURE");
				response.setMessage("Adding New User Failed");
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/users/doLogin", method = RequestMethod.POST)
	@Operation(description = "This Service handles user login on ERP")
    public ResponseEntity<?> doLogin(@RequestBody LoginDTO loginDTO) {
		System.out.println("API Call To Do Login");
		
		try {
			ResponseDTO response = usersService.doLogin(loginDTO);
			if(response.getStatus().equalsIgnoreCase("SUCCESS")) {
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			}else if(response.getStatus().equalsIgnoreCase("EMPTY_TEXTFIELD")) {
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_REQUIRED);
			}else if(response.getStatus().equalsIgnoreCase("ACCOUNT_NONEXISTS") || response.getStatus().equalsIgnoreCase("INVALID_CREDENTIALS")) {
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else {
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
