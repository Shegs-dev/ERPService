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

import com.proinsight.erpservice.dtos.LoginDTO;
import com.proinsight.erpservice.dtos.ResponseDTO;
import com.proinsight.erpservice.dtos.UserFilterDTO;
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
	@Operation(description = "This Service adds/invites new user on ERP")
    public ResponseEntity<?> add(@RequestBody Users user) {
		System.out.println("API Call To Add/Invite New User");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = usersService.add(user);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Invited New User Successfully");
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
				response.setMessage("Inviting New User Failed");
				return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
			}
		}catch(Exception e) {
			System.err.println("Exception occured "+e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/users/completeReg", method = RequestMethod.POST)
	@Operation(description = "This Service completes registration of new user on ERP")
    public ResponseEntity<?> completeReg(@RequestBody Users user) {
		System.out.println("API Call To Complete Registration Of New User");
		
		try {
			ResponseDTO response = new ResponseDTO();
			int retValue = usersService.completeReg(user);
			if(retValue == 1) {
				response.setStatus("SUCCESS");
				response.setMessage("Completed New User Registration Successfully");
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
				response.setStatus("ACCOUNT_DISCREPANCY");
				response.setMessage("User Account ID Does Not Match Request!");
				return new ResponseEntity<>(response, HttpStatus.PRECONDITION_FAILED);
			}else {
				response.setStatus("FAILURE");
				response.setMessage("Completing New User Registration Failed");
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
	
	@RequestMapping(value = "/users/get/{type}", method = RequestMethod.GET)
	@Operation(description = "This Service gets all users")
    public ResponseEntity<List<?>> get(@PathVariable int type){
        System.out.println("API Call To Fetch All Users");
		
		try {
			return new ResponseEntity<>(usersService.get(type), HttpStatus.OK);
		} catch (Exception e) {
            System.err.println("Exception occurred " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@RequestMapping(value = "/users/find", method = RequestMethod.POST)
	@Operation(description = "This Service finds some users")
    public ResponseEntity<List<?>> find(@RequestBody UserFilterDTO filterDTO){
        System.out.println("API Call To Fetch Some Users");
		
		try {
			return new ResponseEntity<>(usersService.find(filterDTO), HttpStatus.OK);
		} catch (Exception e) {
            System.err.println("Exception occurred " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@RequestMapping(value = "/users/getByIds/{ids}", method = RequestMethod.GET)
	@Operation(description = "This Service finds by user ids")
    public ResponseEntity<List<?>> getByIds(@PathVariable List<String> ids){
        System.out.println("API Call To Fetch Users By IDs");
		
		try {
			return new ResponseEntity<>(usersService.getByIDs(ids), HttpStatus.OK);
		} catch (Exception e) {
            System.err.println("Exception occurred " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

}
