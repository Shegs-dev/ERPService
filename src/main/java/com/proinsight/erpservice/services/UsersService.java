/**
 * 
 */
package com.proinsight.erpservice.services;

import java.util.List;

import com.proinsight.erpservice.dtos.LoginDTO;
import com.proinsight.erpservice.dtos.ResponseDTO;
import com.proinsight.erpservice.dtos.UserFilterDTO;
import com.proinsight.erpservice.entities.Users;

/**
 * @author OluwasegunAjayi on 22nd of February 2021
 *
 */
public interface UsersService {
	
	//Service Methods
	int add(Users user);
	int completeReg(Users user);
	List<Users> get(int type);//0 is all, 1 is Candidate, 2 is Admin
	List<Users> find(UserFilterDTO filterDTO);
	List<Users> getByIDs(List<String> ids);
	ResponseDTO doLogin(LoginDTO loginDTO);
	int delete(String email);

}
