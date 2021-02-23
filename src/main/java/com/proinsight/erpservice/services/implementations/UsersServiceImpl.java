/**
 * 
 */
package com.proinsight.erpservice.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proinsight.erpservice.dtos.LoginDTO;
import com.proinsight.erpservice.dtos.ResponseDTO;
import com.proinsight.erpservice.entities.Users;
import com.proinsight.erpservice.repositories.UsersRepository;
import com.proinsight.erpservice.services.UsersService;
import com.proinsight.erpservice.utils.DateConverter;
import com.proinsight.erpservice.utils.HashPassword;

/**
 * @author OluwasegunAjayi on 22nd of February 2021
 *
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {
	
	//private fields
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	DateConverter dateConverter;
	@Autowired
	HashPassword hashPassword;

	@Override
	public int add(Users user) {
		System.out.println("Adding User");
		
		//Validation
		if(user.getCountryOrRegion() == null || user.getCountryOrRegion().isEmpty() || user.getEmail() == null || user.getEmail().isEmpty() || user.getFname() == null || user.getFname().isEmpty() 
				|| user.getFullStreetAddress() == null || user.getFullStreetAddress().isEmpty() || user.getLname() == null || user.getLname().isEmpty()	|| user.getPhone() == null || user.getPhone().isEmpty() 
				|| user.getPostalCode() == null || user.getProvince() == null || user.getProvince().isEmpty() || user.getTown() == null || user.getTown().isEmpty() || user.getType() == 0)
			return 2;
		if(user.getType() < 1 || user.getType() > 3) return 3;
		if(user.getType() == 2 || user.getType() == 3) {
			if(user.getPassword() == null || user.getPassword().isEmpty() || user.getUsername() == null || user.getUsername().isEmpty()) return 2;
		}
		
		try {
			Users usr = usersRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
			if(usr != null) return 4;
			user.setCreatedTime(dateConverter.getCurrentTimestamp());
			if(user.getPassword() != null && !user.getPassword().isEmpty()) user.setPassword(hashPassword.hashPass(user.getPassword()));
			usersRepository.save(user);
			
			return 1;
		}catch(Exception e) {
			System.err.println("Error While Adding User "+e);
			return 0;
		}
	}

	@Override
	public int update(Users user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResponseDTO doLogin(LoginDTO loginDTO) {
		System.out.println("Do Login");
		
		ResponseDTO response = new ResponseDTO();
		//Validation
		if(loginDTO.getPassword() == null || loginDTO.getPassword().isEmpty() || loginDTO.getUsername() == null || loginDTO.getUsername().isEmpty()) {
			response.setStatus("EMPTY_TEXTFIELD");
			response.setMessage("Fill Empty Textfield(s)");
			return response;
		}
		
		try {
			Users user = usersRepository.findByUsernameOrEmail(loginDTO.getUsername(), loginDTO.getUsername());
			if(user == null) {
				response.setStatus("ACCOUNT_NONEXISTS");
				response.setMessage("User Account Does Not Exist!");
				return response;
			}
			
			if(!hashPassword.validatePass(loginDTO.getPassword(), user.getPassword())) {
				response.setStatus("INVALID_CREDENTIALS");
				response.setMessage("Username Or Password Incorrect!");
				return response;
			}
			
			response.setStatus("SUCCESS");
			response.setMessage("Login Successful");
			response.setData(user);
			return response;
		}catch(Exception e) {
			System.err.println("Error While Do Login "+e);
			response.setStatus("FAILURE");
			response.setMessage("Do Login Failed");
			return response;
		}
	}

}
